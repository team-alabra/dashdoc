package com.dashdocapi.services_tests;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.dashdocapi.DTO.ConfirmSignUpDTO;
import com.dashdocapi.DTO.ProviderDTO;
import com.dashdocapi.DTO.UserSignUpRequest;
import com.dashdocapi.entities.Provider;
import com.dashdocapi.services.AuthService;
import com.dashdocapi.services.ProviderService;
import com.dashdocapi.services.vendors.StripeServiceImpl;
import com.dashdocapi.utils.BadRequestException;
import com.dashdocapi.utils.TestData;
import com.stripe.model.Customer;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthServiceTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AuthService authService;
    @MockBean
    private AWSCognitoIdentityProvider cognitoClient;
    @MockBean
    private ProviderService providerService;
    @MockBean
    private StripeServiceImpl stripeService;
    private final MockHttpServletRequest request;
    private final MockHttpServletResponse response;

    public AuthServiceTests() throws UnsupportedEncodingException {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.setParameter("firstName", "Spring");
        request.setParameter("lastName", "Test");
        request.setCookies(new Cookie("access_token", TestData.getTestJwt()));
    }
    @Test
    public void validateUserStatus_UserIsLoggedIn() {
        // Arrange
        var mockUser = new AdminGetUserResult();

        mockUser.setEnabled(true);
        mockUser.withUserAttributes(new AttributeType().withName("email").withValue("test@email.com"));

        when(cognitoClient.adminGetUser(any(AdminGetUserRequest.class))).thenReturn(mockUser);

        // Act
        var authRes = authService.validateUserStatus(request, response);

        //Assert
       Assertions.assertTrue(authRes.isValid());
    }

        @Test
    public void validateUserStatus_UserDoesNotExist() {
            // Arrange
            String expectedMessage = "User not found";
            Exception exception = assertThrows(BadRequestException.class, () -> authService.validateUserStatus(request, response));
            String actualMessage = exception.getMessage();

            // Act
            when(cognitoClient.adminGetUser(any(AdminGetUserRequest.class))).thenThrow(new BadRequestException("User not found"));

            // Assert
            Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    public void signUp_NewProvider () {
        // Arrange
        Provider sampleProvider = TestData.getProviderDBO();
        UserSignUpRequest request = new UserSignUpRequest(
                sampleProvider.getEmail(),
                "123",
                sampleProvider.getUserType(),
                null
        );
        // Act
        when(providerService.save(any(ProviderDTO.class))).thenReturn(sampleProvider.asDTO());

        ProviderDTO unconfirmedProvider = authService.signUp(request);

        // Assert
        Assertions.assertNotNull(unconfirmedProvider);
        Assertions.assertEquals(sampleProvider.getId(), unconfirmedProvider.getId());
    }

    @Test
    public void signUp_ThrowsError () throws Exception {
        // Arrange
        AWSCognitoIdentityProviderException exception = new AWSCognitoIdentityProviderException("Invalid sign up request");
        when(cognitoClient.signUp(any(SignUpRequest.class))).thenThrow(new BadRequestException(exception) {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        // Act & Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/signup",  404))
                .andExpect(status().is(404));
    }

    @Test
    public void confirmSignUp_NewProvider () {
        // Arrange
        Provider sampleProvider = TestData.getProviderDBO();
        ConfirmSignUpDTO request = new ConfirmSignUpDTO(
                sampleProvider.getEmail(),
                "000"
        );
        Customer sampleCustomer = new Customer();

        // Act
        when(providerService.getByEmail(request.getEmail())).thenReturn(sampleProvider.asDTO());
        when(stripeService.createCustomer(sampleProvider.getEmail())).thenReturn(sampleCustomer);
        when(providerService.update(any(ProviderDTO.class))).thenReturn(sampleProvider.asDTO());

        ProviderDTO updatedProvider = authService.confirmSignUpRequest(request);

        // Assert
        Assertions.assertNotNull(updatedProvider);
    }

    @Test
    public void confirmSignUp_ThrowError () throws Exception {
        // Arrange
        when(cognitoClient.confirmSignUp(any(ConfirmSignUpRequest.class))).thenThrow(new BadRequestException("Unable to confirm sign up") {
            @Override
            public String getMessage() {
                return super.getMessage();
            }
        });

        // Act & Assert
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/confirmUser",  404))
                .andExpect(status().is(404));
    }

}
