package com.dashdocapi.services.vendors;

import com.dashdocapi.DTO.ProviderDTO;
import com.dashdocapi.DTO.models.CreateSubscriptionRequest;
import com.dashdocapi.DTO.models.FetchPlanRequest;
import com.dashdocapi.interfaces.StripeService;
import com.dashdocapi.interfaces.enums.SubscriptionStatus;
import com.dashdocapi.services.AgencyService;
import com.dashdocapi.services.ProviderService;
import com.dashdocapi.utils.BadRequestException;
import com.dashdocapi.utils.NotFoundException;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.model.SubscriptionItem;
import com.stripe.model.checkout.Session;
import com.stripe.param.*;
import com.stripe.param.checkout.SessionCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

// responsible for talking to Stripe API for creating customers, charging credit cards and validating coupon codes
@Service
@Transactional
public class StripeServiceImpl implements StripeService {
    @Value("${stripe.secret.key}")
    private String stripeSecret;
    @Value("${STRIPE_SUCCESS_URL:#{''}}")
    private String stripeSuccessURL;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private StripePlanServiceImpl planService;

    public StripeServiceImpl() {
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecret;
    }

    @Override
    public Customer createCustomer(String customerEmail) {
        try {
            // region Find Customer In DB
            var customer = providerService.getByEmail(customerEmail);
            if (customer == null)
                throw new NotFoundException("Customer not found in the database");
            // endregion

            // region Check if Customer is In Stripe already
            String stripeId = customer.getSubscription().getStripeCustomerID();

            if (Customer.retrieve(Objects.requireNonNullElse(stripeId, "")) != null)
                throw new BadRequestException("This customer already exists.");
            // endregion

            // region Create Customer name and if they have an agency, add it to their metadata
            String name = customer.getFirstName() + " " + customer.getLastName();
            String metadata = "N/A";

            if (customer.getAgencyID() != null) {
                var agency = agencyService.getById(customer.getAgencyID());
                metadata = agency.getName();
            }
            // endregion

            // region Create Stripe customer
            var params = CustomerCreateParams.builder()
                            .setName(name)
                            .setEmail(customerEmail)
                            .putMetadata("Agency", metadata)
                            .build();

            return Customer.create(params);
            // endregion
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public com.stripe.model.Subscription createSubscription (String customerEmail, CreateSubscriptionRequest createSubscriptionRequest) {
        try {
            // region Find Customer In DB
            var customer = providerService.getByEmail(customerEmail);
            var subscriptionId = customer.getSubscription().getStripeSubscriptionID();
            var stripeCustomerID = customer.getSubscription().getStripeCustomerID();

            if (subscriptionId != null)
                throw new BadRequestException("This customer already has an active subscription.");
            // endregion

            // region Fetch Stripe plan price code
            var stripeProduct = planService.getByType(new FetchPlanRequest(
                    createSubscriptionRequest.getSubscriptionType(),
                    createSubscriptionRequest.getTerm()
            ));
            // endregion

            // region Create subscription for customer
            var trialEndDate = Calendar.getInstance();
            trialEndDate.add(Calendar.DATE, 14);

            long unixTime = trialEndDate.getTimeInMillis() / 1000L;

            var params = SubscriptionCreateParams.builder()
                    .setCustomer(stripeCustomerID)
                    .setDefaultPaymentMethod(null)
                    .setTrialEnd(unixTime)
                    .addItem(
                            SubscriptionCreateParams.Item.builder()
                                    .setPrice(stripeProduct.getDefaultPrice())
                                    .setQuantity(1L)
                                    .build()
                    )
                    .build();
            return com.stripe.model.Subscription.create(params);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public SubscriptionItem addAgencyProvider(String customerEmail) {
        try {
            var customer = providerService.getByEmail(customerEmail);
            if (customer == null)
                throw new BadRequestException("Customer not found in the database");

            var planId = customer.getSubscription().getStripePlanID();
            var resource = SubscriptionItem.retrieve(Objects.requireNonNullElse(planId, ""));
            var params = SubscriptionItemUpdateParams.builder()
                    .setQuantity(resource.getQuantity() + 1)
                    .build();

            return resource.update(params);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public com.stripe.model.Subscription cancelSubscription(String customerEmail) {
        try {
            var myProvider = providerService.getByEmail(customerEmail);
            String providerSubscriptionID = myProvider.getSubscription().getStripeSubscriptionID();
            myProvider.getSubscription().setStatus(SubscriptionStatus.valueOf("CANCELED"));

            var result = com.stripe.model.Subscription.retrieve(providerSubscriptionID);

            return result.cancel();
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    @Override
    public String postCheckout(String checkoutSessionString) throws StripeException {
        Session session = Session.retrieve(checkoutSessionString);

        Customer sessionCustomer = Customer.retrieve(session.getCustomer());

        var myProvider = providerService.getByEmail(sessionCustomer.getEmail());
        myProvider.getSubscription().setStripeSubscriptionID(session.getSubscription());

        providerService.update(myProvider);

        return "successfully saved newly created subscription in database!";
    }

    @Override
    public String createChargeSession(String customerEmail) throws BadRequestException {
        try {
            var customer = providerService.getByEmail(customerEmail);
            var sub = com.stripe.model.Subscription.retrieve(customer.getSubscription().getStripeSubscriptionID());

            String priceCode = sub.getItems().getData().get(0).getPrice().getId();

            ProviderDTO myProvider = providerService.getByEmail(customerEmail);
            String stripeCustomerID = myProvider.getSubscription().getStripeCustomerID();

            var params = SessionCreateParams.builder()
                        .setSuccessUrl(stripeSuccessURL)
                        .setCustomer(stripeCustomerID)
                        .addLineItem(
                                SessionCreateParams.LineItem.builder()
                                .setPrice(priceCode)
                                .setQuantity(1L)
                                .build()
                        )
                        .setMode(SessionCreateParams.Mode.PAYMENT)
                        .build();

            Session session = Session.create(params);

            return session.getUrl();

        } catch (StripeException ex) {
            throw new BadRequestException(ex);
        }
    }

    @Override
    public com.stripe.model.Subscription setDefaultPaymentMethod(String customerEmail, String paymentMethodId) throws StripeException {
        var customer = providerService.getByEmail(customerEmail);
        var resource = com.stripe.model.Subscription.retrieve(customer.getSubscription().getStripeSubscriptionID());

        resource.setDefaultPaymentMethod(paymentMethodId);

        return resource;
    }

    @Override
    public PaymentMethodCollection getAllPaymentMethods(String customerEmail) throws StripeException {
        var customer = providerService.getByEmail(customerEmail);
        var resource = Customer.retrieve(customer.getSubscription().getStripeCustomerID());
        var params = CustomerListPaymentMethodsParams.builder().setLimit(5L).build();

        return resource.listPaymentMethods(params);
    }
}
