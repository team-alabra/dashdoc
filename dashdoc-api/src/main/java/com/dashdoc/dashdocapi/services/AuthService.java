package com.dashdocnow.services;

import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import com.dashdocnow.DTO.*;
import com.dashdocnow.interfaces.enums.SubscriptionStatus;
import com.dashdocnow.interfaces.enums.UserType;
import com.dashdocnow.services.vendors.StripeServiceImpl;
import com.dashdocnow.utils.BadRequestException;
import com.dashdocnow.utils.TokenUtil;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.stripe.model.Customer;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class AuthService {
    @Value(value = "${aws.cognito.userPoolId}")
    private String userPoolId;
    @Value(value = "${aws.cognito.clientId}")
    private String clientId;
    @Value(value="${google.auth.clientId}")
    private String googleAuthClientId;
    @Value(value="${google.issuer.uri}")
    private String googleAuthIssuerURI;
    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;
    @Autowired
    private ProviderService providerService;
    @Autowired
    private AgencyService agencyService;
    @Autowired
    private StripeServiceImpl stripeService;

     private static List<AttributeType> getUserAttributes(String emailAddress) {
        ArrayList<AttributeType> attributes = new ArrayList<>();
        attributes.add(new AttributeType().withName("email").withValue(emailAddress));
        return attributes;
    }

    private static void setCookieProperties(Cookie cookie) {
        cookie.setHttpOnly(false);
        cookie.setPath("/");
        cookie.setSecure(false);
    };

    // TODO: 3/12/23 Add an email regex to Cognito
    public ProviderDTO signUp(UserSignUpRequest userSignUpRequest) {
        UserType userType = userSignUpRequest.getUserType();
        AgencyDTO agencyInDB = null;

        try {
            SignUpRequest unconfirmedCognitoUser = new SignUpRequest()
                    .withUsername(userSignUpRequest.getEmail())
                    .withClientId(clientId)
                    .withPassword(userSignUpRequest.getPassword())
                    .withUserAttributes(getUserAttributes(userSignUpRequest.getEmail()));

            // region create agency if user is agency admin
            if (userSignUpRequest.getUserType() == UserType.AGENCY_ADMINISTRATOR) {
                agencyInDB = agencyService.save(new AgencyDTO(userSignUpRequest.getEmail(), userSignUpRequest.getAgencyName()));
            }
            // endregion

            ProviderDTO unconfirmedProvider = new ProviderDTO(
                    userSignUpRequest.getFirstName(),
                    userSignUpRequest.getLastName(),
                    userSignUpRequest.getEmail(),
                    userType,
                    agencyInDB != null ? agencyInDB.getId() : null
            );

            cognitoClient.signUp(unconfirmedCognitoUser);

            cognitoClient.adminAddUserToGroup(new AdminAddUserToGroupRequest()
                    .withGroupName(userType.name())
                    .withUsername(userSignUpRequest.getEmail())
                    .withUserPoolId(userPoolId)
            );

            return providerService.save(unconfirmedProvider);
        } catch (AWSCognitoIdentityProviderException e) {
            throw new BadRequestException("An account with the given email already exists.");
        } catch (DataIntegrityViolationException e) {
            // handles duplicate agency name and/or agency email values
            throw new BadRequestException("An account with the given email already exists.");
        } catch (BadRequestException e) {
          throw new BadRequestException(e.getMessage());
        }
    }


    // user cannot sign in, if they are unconfirmed.
    public ProviderDTO signIn(UserSignInRequest userSignInRequest, HttpServletResponse response) throws BadRequestException {
        try {
            ProviderDTO providerSigningIn = providerService.getByEmail(userSignInRequest.getEmail());

            if (providerSigningIn.getSubscription().getStatus() == SubscriptionStatus.ACTIVE) {
                // Run signin helper
                final AdminInitiateAuthResult authReq = signInConfigHelper(userSignInRequest);
                AuthenticationResultType authRes = authReq.getAuthenticationResult();

                // Set authenticated user data in cookies
                Cookie accessToken = buildCookie("access_token", authRes.getAccessToken(), authRes.getExpiresIn());
                Cookie refreshToken = buildCookie("refresh_token", authRes.getRefreshToken(), authRes.getExpiresIn());

                setCookieProperties(accessToken);
                setCookieProperties(refreshToken);

                response.addCookie(accessToken);
                response.addCookie(refreshToken);
                return providerSigningIn;
            } else {
                throw new Exception("Please choose a subscription plan before proceeding!");
            }
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    private Cookie buildCookie(String key, String value, int expires_in) {
        Cookie cookie = new Cookie(key, value);
        cookie.setSecure(false);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(expires_in);

        return cookie;
    }

    private AdminInitiateAuthResult signInConfigHelper(UserSignInRequest userSignInRequest) throws BadRequestException {
        try {
            final Map<String, String> authParams = new HashMap<String, String>();
            authParams.put("USERNAME", userSignInRequest.getEmail());
            authParams.put("PASSWORD", userSignInRequest.getPassword());
            authParams.put("EMAIL", userSignInRequest.getEmail());

            final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
            authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId(clientId)
                    .withUserPoolId(userPoolId).withAuthParameters(authParams);

            System.out.println("Successfully signed in!");
            return cognitoClient.adminInitiateAuth(authRequest);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProviderDTO confirmSignUpRequest(ConfirmSignUpDTO confirmSignUpDTO) {
        try {

             cognitoClient
                    .confirmSignUp(new ConfirmSignUpRequest()
                            .withUsername(confirmSignUpDTO.getEmail())
                                .withClientId(clientId)
                                    .withConfirmationCode(confirmSignUpDTO.getOtpCode()));

            //Get Entities from DB
            ProviderDTO providerDTO = providerService.getByEmail(confirmSignUpDTO.getEmail());
            SubscriptionDTO providerSubscription = providerDTO.getSubscription();

            //region create customer in Stripe & set id in database, activate subscription
            Customer customer = stripeService.createCustomer(providerDTO.getEmail());
            providerSubscription.setStripeCustomerID(customer.getId());
            providerSubscription.setStatus(SubscriptionStatus.ACTIVE);
            // endregion

            return providerService.update(providerDTO);
        } catch (CodeMismatchException e) {
            throw new BadRequestException("Invalid verification code provided, please try again.");
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    };

    public ResendConfirmationCodeResult resendConfirmationCode(ConfirmSignUpDTO confirmSignUpDTO) {
        ProviderDTO provider = providerService.getByEmail(confirmSignUpDTO.getEmail());
        try {
            ResendConfirmationCodeRequest codeRequest = new ResendConfirmationCodeRequest();
            codeRequest
                    .withClientId(clientId)
                    .withUsername(provider.getEmail());

            return cognitoClient.resendConfirmationCode(codeRequest);

        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }

    }

    public void refreshAuthToken(HttpServletRequest req, HttpServletResponse res) {
        String refreshToken = TokenUtil.getTokenFromCookie(req, "refresh_token");

        final Map<String, String> authParams = new HashMap<>();
        authParams.put("REFRESH_TOKEN", refreshToken);

        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest
                .withAuthFlow(AuthFlowType.REFRESH_TOKEN_AUTH)
                .withClientId(clientId)
                .withUserPoolId(userPoolId)
                .withAuthParameters(authParams);

        var authReq = cognitoClient.adminInitiateAuth(authRequest);

        AuthenticationResultType authRes = authReq.getAuthenticationResult();

        // Set authenticated user data in cookies
        Cookie accessToken = buildCookie("access_token", authRes.getAccessToken(), authRes.getExpiresIn());

        setCookieProperties(accessToken);

        res.addCookie(accessToken);
    }

    public ValidateUserDTO validateUserStatus(HttpServletRequest req, HttpServletResponse res) {
        try {
            ValidateUserDTO validateUserResult = new ValidateUserDTO();
            String accessToken = TokenUtil.getTokenFromCookie(req, "access_token");
            String userName = TokenUtil.getUserFromToken(accessToken, "username");

            final AdminGetUserRequest adminReq = new AdminGetUserRequest();
            adminReq
                    .withUserPoolId(userPoolId)
                    .withUsername(userName);

            var user = cognitoClient.adminGetUser(adminReq);

            validateUserResult.setValid(user.getEnabled());
            validateUserResult.setEmail(userName);

            return validateUserResult;
        } catch(Exception e) {
            throw new BadRequestException("User not found");
        }
    }

    public AdminGetUserRequest cognitoSignInPostGoogleAuth (GoogleAuthRequest googleAuthRequest) {
        try {
            final AdminGetUserRequest adminReq = new AdminGetUserRequest();
            adminReq
                    .withUserPoolId(userPoolId)
                    .withUsername(googleAuthRequest.getEmail());

//            var user = cognitoClient.adminGetUser(adminReq);
//            System.out.println("THE USER ---->" + user.getUserAttributes());
            return adminReq;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new BadRequestException(e.getMessage());
        }
    }

    public ProviderDTO validateGoogleUser(GoogleAuthRequest googleAuthRequest, HttpServletResponse res) throws Exception {
        try {
            NetHttpTransport transport = new NetHttpTransport();
            JsonFactory jsonFactory = new GsonFactory();

            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                    .setAudience(Collections.singletonList(googleAuthClientId))
                    .setIssuer(googleAuthIssuerURI)
                    .build();

          final GoogleIdToken idToken = GoogleIdToken.parse(jsonFactory, googleAuthRequest.getIdToken());
          GoogleIdToken result = verifier.verify(googleAuthRequest.getIdToken());

            if (result != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();

                // Print user identifier
                String sub = payload.getSubject();

                // Get profile information from payload
                String email = payload.getEmail();

                ProviderUserIdentifierType sourceUser = new ProviderUserIdentifierType();
                sourceUser.setProviderAttributeName("Cognito_Subject");
                sourceUser.setProviderName("Google");
                sourceUser.setProviderAttributeValue(sub);

                ProviderUserIdentifierType destinationUser = new ProviderUserIdentifierType();
                destinationUser.setProviderName("Cognito");
                destinationUser.setProviderAttributeName("email");
                destinationUser.setProviderAttributeValue(email);

                // region Cognito call try/catch block - used to catch specific Cognito errors.
                try {
                    cognitoClient.adminLinkProviderForUser(
                            new AdminLinkProviderForUserRequest()
                                    .withSourceUser(sourceUser)
                                    .withDestinationUser(destinationUser)
                                    .withUserPoolId(userPoolId)
                    );

                } catch(Exception e) {
                    throw new BadRequestException(e.getMessage());
                }
                // endregion

                // grab user from DB

                return providerService.getByEmail(email);
            } else {
                throw new Exception("Invalid Google token");
            }

        } catch(Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

}


