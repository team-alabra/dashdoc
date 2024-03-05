package com.dashdocnow.controllers;

import com.dashdocnow.DTO.models.FetchPlanRequest;
import com.dashdocnow.services.vendors.StripePlanServiceImpl;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.ProductCollection;
import com.stripe.model.ProductSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plan")
public class PlanController {
    @Autowired
    private StripePlanServiceImpl stripePlanServiceImpl;

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable String id) throws StripeException {
        var myPlan = stripePlanServiceImpl.getById(id);

        return new ResponseEntity<>(
                myPlan,
                HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Product> getByType(@RequestBody FetchPlanRequest req) throws StripeException {
        var myPlan = stripePlanServiceImpl.getByType(req);

        return new ResponseEntity<>(
                myPlan,
                HttpStatus.OK);
    }

    @GetMapping("/all-plans")
    public ResponseEntity<ProductCollection> getAllPlans() throws StripeException {
        var plans = stripePlanServiceImpl.getAllPlans();

        return new ResponseEntity<>(
                plans,
                HttpStatus.OK);
    }

    @GetMapping("/agency-plans")
    public ResponseEntity<List<Product>> getAgencyPlans() throws StripeException {
        var plans = stripePlanServiceImpl.getAgencyPlans();

        return new ResponseEntity<>(
                plans,
                HttpStatus.OK);
    }

    @GetMapping("/provider-plans")
    public ResponseEntity<List<Product>> getProviderPlans() throws StripeException {
        var plans = stripePlanServiceImpl.getSoleProviderPlans();

        return new ResponseEntity<>(
                plans,
                HttpStatus.OK);
    }

    @PutMapping ("/update")
    public ResponseEntity<Product> update() {
        return null;
    }

}
