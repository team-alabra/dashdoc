package com.dashdocapi.interfaces;

import com.dashdocapi.DTO.models.CreateSubscriptionRequest;
import com.dashdocapi.utils.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.PaymentMethodCollection;
import com.stripe.model.SubscriptionItem;

public interface StripeService {
    Customer createCustomer(String customerEmail);
    com.stripe.model.Subscription createSubscription (String customerEmail, CreateSubscriptionRequest createSubscriptionRequest);
    SubscriptionItem addAgencyProvider(String customerEmail) throws StripeException, JsonProcessingException;
    com.stripe.model.Subscription cancelSubscription(String customerEmail);
    com.stripe.model.Subscription setDefaultPaymentMethod(String customerEmail, String paymentMethodId) throws StripeException;
    PaymentMethodCollection getAllPaymentMethods(String customerEmail) throws StripeException;
    String postCheckout (String checkoutSessionString) throws StripeException;
    String createChargeSession(String customerEmail) throws StripeException, BadRequestException;
}
