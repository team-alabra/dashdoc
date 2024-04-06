package com.dashdocapi.services_tests;

import com.dashdocapi.DTO.models.FetchPlanRequest;
import com.dashdocapi.interfaces.enums.UserType;
import com.dashdocapi.services.ProviderService;
import com.dashdocapi.services.vendors.StripePlanServiceImpl;
import com.dashdocapi.services.vendors.StripeServiceImpl;
import com.dashdocapi.utils.BadRequestException;
import com.dashdocapi.utils.StripeTestData;
import com.dashdocapi.utils.TestData;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.param.*;
import io.jsonwebtoken.lang.Assert;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class StripeServiceTests {
    @MockBean
    private ProviderService mockProviderService;
    private final StripePlanServiceImpl mockPlanService;
    @Autowired
    private StripeServiceImpl stripeService;

    public StripeServiceTests(){
        mockPlanService = mock(StripePlanServiceImpl.class);
    }

    @Test
    public void createCustomer_returns_StripeCustomer() {
        // Arrange
        var mockSearchResult = new CustomerSearchResult();
        mockSearchResult.setData(new ArrayList<>());

        MockedStatic<Customer> mockCustomerApi = mockStatic(Customer.class);
        when(mockProviderService.getByEmail(anyString())).thenReturn(TestData.getProviderDBO().asDTO());
        mockCustomerApi.when(() -> Customer.search(any(CustomerSearchParams.class))).thenReturn(null);
        mockCustomerApi.when(() -> Customer.retrieve(anyString())).thenReturn(null);
        mockCustomerApi.when(() -> Customer.create(any(CustomerCreateParams.class))).thenReturn(StripeTestData.getStripeCustomer());

        // Act
        var result = stripeService.createCustomer("testerson@gmail.com");
        mockCustomerApi.close();

        // Assert
        Assert.notNull(result);
    }

    @Test
    public void createCustomer_fails_noCustomer_InDB() {
        // Arrange
        when(mockProviderService.getByEmail(any(String.class))).thenReturn(null);

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> stripeService.createCustomer(any(String.class)));
        String actualMessage = exception.getMessage();

        // Assert
        assertTrue(actualMessage.contains("Customer not found in the database"));
    }

    @Test
    public void createCustomer_fails_customer_AlreadyExists() {
        // Arrange
        MockedStatic<Customer> mockCustomerApi = mockStatic(Customer.class);
        when(mockProviderService.getByEmail(anyString())).thenReturn(TestData.getProviderDBO().asDTO());
        mockCustomerApi.when(() -> Customer.search(any(CustomerSearchParams.class))).thenReturn(StripeTestData.getCustomerSearchResult(true));

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> stripeService.createCustomer("test@example.com"));
        String actualMessage = exception.getMessage();
        mockCustomerApi.close();

        // Assert
        assertEquals("This customer already exists.", actualMessage);

    }

    @SneakyThrows
    @Test
    public void createSubscription_returns_newSubscription() {
        // Arrange
        var mockSubscriptionApi = mockStatic(com.stripe.model.Subscription.class);
        var testCustomer = TestData.getProviderDBO().asDTO();
        testCustomer.getSubscription().setStripeSubscriptionID(null);

        when(mockProviderService.getByEmail(any(String.class))).thenReturn(testCustomer);
        when(mockPlanService.getByType(any(FetchPlanRequest.class))).thenReturn(new Product());
        mockSubscriptionApi
                .when(() -> com.stripe.model.Subscription.search(any(SubscriptionSearchParams.class)))
                .thenReturn(StripeTestData.getSubscriptionSearchResult(false));
        mockSubscriptionApi
                .when(() -> com.stripe.model.Subscription.create(any(SubscriptionCreateParams.class)))
                .thenReturn(new com.stripe.model.Subscription());

        // Act
        var result = stripeService.createSubscription("testerson@gmail.com", UserType.SOLE_PROVIDER);
        mockSubscriptionApi.close();

        // Assert
        Assert.notNull(result);
    }

    @SneakyThrows
    @Test
    public void createSubscription_fails_CustomerAlreadyHasSubscription() {
        // Arrange
        var mockSubscriptionApi = mockStatic(com.stripe.model.Subscription.class);
        mockSubscriptionApi
                .when(() -> com.stripe.model.Subscription.search(any(SubscriptionSearchParams.class)))
                .thenReturn(StripeTestData.getSubscriptionSearchResult(true));

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> stripeService.createSubscription("test@email.com", UserType.SOLE_PROVIDER));
        String actualMessage = exception.getMessage();
        mockSubscriptionApi.close();

        // Assert
        assertEquals("This customer already has an active subscription.", actualMessage);
    }

    @Test
    public void addAgencyProvider_returns_UpdatedStripeCustomer() throws StripeException {
        // Arrange
        var mockSubscriptionItemApi = mockStatic(SubscriptionItem.class);
        var item = mock(SubscriptionItem.class);
        item.setQuantity(1L);

        when(mockProviderService.getByEmail(anyString())).thenReturn(TestData.getProviderDBO().asDTO());
        mockSubscriptionItemApi.when(() -> SubscriptionItem.retrieve(anyString())).thenReturn(item);
        when(item.update(any(SubscriptionItemUpdateParams.class))).thenReturn(new SubscriptionItem());

        // Act
        var result = stripeService.addAgencyProvider("test@email.com");
        mockSubscriptionItemApi.close();

        // Assert
        assertNotNull(result);
    }

    @Test
    public void addAgencyProvider_fails_ResourceNotFound() {
        // Arrange
        MockedStatic<SubscriptionItem> mockSubscriptionItemApi = mockStatic(SubscriptionItem.class);
        when(mockProviderService.getByEmail(anyString())).thenReturn(TestData.getProviderDBO().asDTO());
        // here I use this callback (invocation) -> {.....} because for some reason,
        // I can't mock thenThrows with a MockedStatic type.
        mockSubscriptionItemApi.when(() -> SubscriptionItem.retrieve(anyString()))
                .then(invocation -> {
                    throw new Exception("Exception thrown when item not found");
                });

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> stripeService.addAgencyProvider("test@email.com"));
        String actualMessage = exception.getMessage();
        mockSubscriptionItemApi.close();

        // Assert
        assertEquals("Exception thrown when item not found", actualMessage);
    }
    @SneakyThrows
    @Test
    public void cancelSubscription_successful_CallsCancelApi() {
        // Arrange
        var mockSubscriptionApi = mockStatic(com.stripe.model.Subscription.class);
        var mockSubscription = mock(com.stripe.model.Subscription.class);

        when(mockProviderService.getByEmail(anyString())).thenReturn(TestData.getProviderDBO().asDTO());
        mockSubscriptionApi.when(() -> com.stripe.model.Subscription.retrieve(anyString()))
                .thenReturn(mockSubscription);
        when(mockSubscription.cancel()).thenReturn(new com.stripe.model.Subscription());

        // Act
        var result = stripeService.cancelSubscription("test@email.com");
        mockSubscriptionApi.close();

        // Assert
        assertNotNull(result);
    }

    @Test
    public void cancelSubscription_fails_NoSubscriptionFound() {
        // Arrange
        var mockSubscriptionApi = mockStatic(com.stripe.model.Subscription.class);

        when(mockProviderService.getByEmail(anyString())).thenReturn(TestData.getProviderDBO().asDTO());
        mockSubscriptionApi.when(() -> com.stripe.model.Subscription.retrieve(anyString()))
                .then(invocation -> {
                    throw new Exception("Exception thrown when item not found");
                });

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> stripeService.cancelSubscription("test@email.com"));
        String actualMessage = exception.getMessage();
        mockSubscriptionApi.close();

        // Assert
        assertEquals("Exception thrown when item not found", actualMessage);
    }
}