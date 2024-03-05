package com.dashdocnow.services_tests;

import com.dashdocnow.DTO.models.CreateSubscriptionRequest;
import com.dashdocnow.DTO.models.FetchPlanRequest;
import com.dashdocnow.services.ProviderService;
import com.dashdocnow.services.vendors.StripePlanServiceImpl;
import com.dashdocnow.services.vendors.StripeServiceImpl;
import com.dashdocnow.utils.BadRequestException;
import com.dashdocnow.utils.TestData;
import com.stripe.exception.StripeException;
import com.stripe.model.Product;
import com.stripe.model.SubscriptionItem;
import com.stripe.param.CustomerCreateParams;
import com.stripe.param.SubscriptionCreateParams;
import com.stripe.param.SubscriptionItemUpdateParams;
import io.jsonwebtoken.lang.Assert;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.stripe.model.Customer;

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
        MockedStatic<Customer> mockCustomerApi = mockStatic(Customer.class);
        when(mockProviderService.getByEmail(anyString())).thenReturn(TestData.getProviderDBO().asDTO());
        mockCustomerApi.when(() -> Customer.retrieve(anyString())).thenReturn(null);
        mockCustomerApi.when(() -> Customer.create(any(CustomerCreateParams.class))).thenReturn(TestData.getStripeCustomer());

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
        mockCustomerApi.when(() -> Customer.retrieve(anyString())).thenReturn(TestData.getStripeCustomer());

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
                .when(() -> com.stripe.model.Subscription.create(any(SubscriptionCreateParams.class)))
                .thenReturn(new com.stripe.model.Subscription());

        // Act
        var result = stripeService.createSubscription("testerson@gmail.com", new CreateSubscriptionRequest());
        mockSubscriptionApi.close();

        // Assert
        Assert.notNull(result);
    }

    @SneakyThrows
    @Test
    public void createSubscription_fails_CustomerAlreadyHasSubscription() {
        // Arrange
        when(mockProviderService.getByEmail(anyString())).thenReturn(TestData.getProviderDBO().asDTO());

        // Act
        Exception exception = assertThrows(BadRequestException.class, () -> stripeService.createSubscription("test@email.com", new CreateSubscriptionRequest() ));
        String actualMessage = exception.getMessage();

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