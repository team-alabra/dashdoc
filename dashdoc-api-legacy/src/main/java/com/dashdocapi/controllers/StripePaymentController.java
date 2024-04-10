package com.dashdocapi.controllers;

import com.dashdocapi.interfaces.enums.UserType;
import com.dashdocapi.services.vendors.StripeServiceImpl;
import com.dashdocapi.utils.BadRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.stripe.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/payment")
public class StripePaymentController {
    @Autowired
    private StripeServiceImpl stripeServiceImpl;

    // create charge session with subscription plan they want
    @PostMapping("/checkout-session")
    public ResponseEntity<String> createChargeSession(HttpServletRequest req) throws BadRequestException {
        String sessionUrl = stripeServiceImpl.createChargeSession((String) req.getAttribute("username"));
        return new ResponseEntity<>(
                sessionUrl,
                HttpStatus.OK);
    }

    @GetMapping("/success")
    public ResponseEntity<String> successRoute(@RequestParam String session_id) throws StripeException {
        var result = stripeServiceImpl.postCheckout(session_id);
        return new ResponseEntity<>(
                result,
                HttpStatus.OK);
    }

    @GetMapping("/fail")
    public ResponseEntity<String> failRoute() {
        return new ResponseEntity<>(
                "Failure!",
                HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/cancel-subscription")
    public ResponseEntity<com.stripe.model.Subscription> cancelSubscription(HttpServletRequest req) {
        var result = stripeServiceImpl.cancelSubscription((String) req.getAttribute("username"));
        return new ResponseEntity<>(
                result,
                HttpStatus.OK);
    }

    @PostMapping("/agency/add-employee")
    public ResponseEntity<String> addAgencyEmployee(HttpServletRequest req) throws StripeException, JsonProcessingException {
        stripeServiceImpl.addAgencyProvider((String) req.getAttribute("username"));
        return new ResponseEntity<>(
                "successfully added customer to Agency",
                HttpStatus.OK);
    }

    @PostMapping("/create-customer")
    public ResponseEntity<String> createCustomer(HttpServletRequest req) {
        stripeServiceImpl.createCustomer((String) req.getAttribute("username"));
        return new ResponseEntity<>(
                "Successfully added the Stripe user",
                HttpStatus.OK);
    }

    @PostMapping("/create-subscription")
    public ResponseEntity<String> createSubscription(HttpServletRequest req, @RequestParam UserType userType) {
        stripeServiceImpl.createSubscription((String) req.getAttribute("username"), userType);
        return new ResponseEntity<>(
                "Successfully created subscription.",
                HttpStatus.OK);
    }
}
