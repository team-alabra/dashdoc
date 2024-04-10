package com.dashdocapi.utils;

import com.stripe.model.*;

import java.util.ArrayList;
import java.util.Map;

public class StripeTestData {
    public static Customer getStripeCustomer(){
        var customer = new Customer();
        customer.setId("ppp");
        customer.setName("Testy Testerson");
        customer.setEmail("testerson@gmail.com");
        customer.setMetadata(Map.of("Agency", "N/A"));

        return customer;
    }

    public static Customer getStripeAgencyCustomer(){
        var customer = new Customer();
        customer.setId("ppp");
        customer.setName("Testy Testerson");
        customer.setEmail("testerson@gmail.com");
        customer.setMetadata(Map.of("Agency", "Brooklyn Agency LLC"));

        return customer;
    }

    public static SubscriptionSearchResult getSubscriptionSearchResult(boolean subscriptionFound) {
        var mockSearchResult = new SubscriptionSearchResult();

        if (subscriptionFound) {
            var mockResultData = new ArrayList<com.stripe.model.Subscription>();
            mockResultData.add(new com.stripe.model.Subscription());
            mockSearchResult.setData(mockResultData);
        }

        return mockSearchResult;
    }

    public static CustomerSearchResult getCustomerSearchResult(boolean customerFound) {
        var mockSearchResult = new CustomerSearchResult();

        if (customerFound) {
            var mockResultData = new ArrayList<Customer>();
            mockResultData.add(new Customer());
            mockSearchResult.setData(mockResultData);
        }

        return mockSearchResult;
    }
    /***
     * Returns a Subscription item similar to this:
     * https://docs.stripe.com/api/subscriptions/object
     ***/
    public static com.stripe.model.Subscription getSubscription() {
        var result = new com.stripe.model.Subscription();
        var items = new SubscriptionItemCollection();
        var itemList = new ArrayList<SubscriptionItem>();
        var plan = new SubscriptionItem();
        plan.setId("si_333dzxyzY5fwHx");
        itemList.add(plan);
        items.setData(itemList);
        result.setItems(items);

        return result;
    }
}
