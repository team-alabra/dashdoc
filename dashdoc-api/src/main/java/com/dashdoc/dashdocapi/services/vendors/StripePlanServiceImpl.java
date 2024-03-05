package com.dashdocnow.services.vendors;

import com.dashdocnow.DTO.models.FetchPlanRequest;
import com.dashdocnow.interfaces.StripePlanService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import com.stripe.param.ProductListParams;
import com.stripe.param.ProductSearchParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StripePlanServiceImpl implements StripePlanService {
    @Value("${stripe.secret.key}")
    private String stripeSecret;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecret;
    }

    public Product getById(String id) throws StripeException {
        return Product.retrieve(id);
    }

    public Product getByType(FetchPlanRequest fetchPlanRequest) throws StripeException {
        var type = fetchPlanRequest.getPlanType().name();
        var term = fetchPlanRequest.getPlanTerm().name();
        var queryString = String.format("active:'true' AND metadata['plan_type']:'%s' AND metadata['plan_term']:'%s'", type, term);
        var params = ProductSearchParams
                .builder()
                .setQuery(queryString)
                .build();

        var result = Product.search(params);

        return result.getData().get(0);
    }

    public List<Product> getAgencyPlans() throws StripeException {
        var params = ProductSearchParams
                .builder()
                .setQuery("active:'true' AND metadata['plan_type']:'agency'")
                .build();
        var result = Product.search(params);

        return result.getData();
    }

    public List<Product> getSoleProviderPlans() throws StripeException {
        var params = ProductSearchParams
                .builder()
                .setQuery("active:'true' AND metadata['plan_type']:'individual'")
                .build();
        var result = Product.search(params);

        return result.getData();
    }

    public ProductCollection getAllPlans() throws StripeException {
        var params = ProductListParams.builder().build();
        return Product.list(params);
    }
}
