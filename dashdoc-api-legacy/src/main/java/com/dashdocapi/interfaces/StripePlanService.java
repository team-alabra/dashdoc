package com.dashdocapi.interfaces;

import com.dashdocapi.DTO.models.FetchPlanRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;

import java.util.List;

public interface StripePlanService  {
    Product getById(String id) throws StripeException;
    Product getByType(FetchPlanRequest fetchPlanRequest) throws StripeException;
    List<Product> getAgencyPlans() throws StripeException;
    List<Product> getSoleProviderPlans() throws StripeException;
    ProductCollection getAllPlans() throws StripeException;
}
