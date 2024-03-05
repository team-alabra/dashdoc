package com.dashdocnow;

import com.dashdocnow.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

// *** some documentation on authorization ***
// https://stackoverflow.com/questions/67924811/how-to-use-jwks-with-spring

// *** Filter Ordering documentation ***
// https://docs.spring.io/spring-security/reference/servlet/architecture.html#servlet-securityfilterchain
@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {
    private TokenUtil tokenUtil = new TokenUtil();
    @Autowired
    private Environment environment;

    @Bean
    public SecurityFilterChain filterChainSetup(HttpSecurity http) throws Exception {
        try {
            // disable Cross Site Request Forgery (CSRF)
            // In general not required for stateless Rst APIs that use CRUD
            http.csrf().disable();

            // Local profile allows access to any and all routes
            String currentProfile = Arrays.stream(this.environment.getActiveProfiles()).toList().get(0);

            if (currentProfile.equals("local")) {
                return http.authorizeHttpRequests(authz -> authz.anyRequest().anonymous()).build();
            }

            // Access to plans and auth is available on all profiles
            http.authorizeHttpRequests(authz ->
                    authz
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                        .requestMatchers( "/built/**").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/plan/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE).hasAuthority("ADMIN"));
            // USER
            http.authorizeHttpRequests(authz ->
                    authz
                        .requestMatchers("/api/user/{id}/cancelSubscription").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR")
                        .requestMatchers("/api/user/save").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers("/api/user/update").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers("/api/user/{id}/clients").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers(HttpMethod.GET, "/api/user/{id}").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER"));
            // AGENCY
            http.authorizeHttpRequests(authz ->
                    authz
                        .requestMatchers("/api/agency/save").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers("/api/agency/update").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers("/api/agency/{id}/clients").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers("/api/agency/{id}/providers").hasAnyAuthority("ADMIN", "AGENCY_ADMINISTRATOR")
                        .requestMatchers(HttpMethod.GET, "/api/agency/{id}").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER"));
            // CLIENT
            http.authorizeHttpRequests(authz ->
                    authz
                        .requestMatchers("/api/client/save").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers("/api/client/update").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers(HttpMethod.GET, "/api/client/{id}").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER"));
            // PAYMENT
            http.authorizeHttpRequests(authz ->
                    authz
                        .requestMatchers("/api/payment/checkout-session").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR")
                        .requestMatchers("/api/payment/success").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR")
                        .requestMatchers("/api/payment/fail").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR")
                        .requestMatchers("/api/payment/update-subscription").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/payment/create-customer").hasAnyAuthority("ADMIN")
                        .requestMatchers("/api/payment/create-subscription").hasAnyAuthority("ADMIN")
            );
            // APPOINTMENT
            http.authorizeHttpRequests(authz ->
                    authz
                        .requestMatchers("/api/appointment/save").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers("/api/appointment/update").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers(HttpMethod.GET, "/api/appointment/{id}").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER"));

            // DOCUMENT
            http.authorizeHttpRequests(authz ->
                    authz
                        .requestMatchers("/api/document/save").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers("/api/document/update").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .requestMatchers(HttpMethod.GET, "/api/document/{id}").hasAnyAuthority("ADMIN", "SOLE_PROVIDER", "AGENCY_ADMINISTRATOR", "AGENCY_PROVIDER")
                        .anyRequest().permitAll());

            // Uses cognito:roles to check authorities
            http.oauth2ResourceServer(oauth2 -> oauth2.jwt().jwtAuthenticationConverter(tokenUtil.jwtAuthenticationConverter()));

            return http.build();
        } catch (Exception ex) {
            throw ex;
        }
    }
}
