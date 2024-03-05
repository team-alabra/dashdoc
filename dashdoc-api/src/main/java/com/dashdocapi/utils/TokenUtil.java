package com.dashdocapi.utils;

import com.nimbusds.jwt.JWTParser;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

@Component
public class TokenUtil implements Filter {
    @Autowired
    private Environment environment;

    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName("cognito:groups");
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);

        return jwtAuthenticationConverter;
    }

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        var currentProfile = Arrays.stream(this.environment.getActiveProfiles()).toList().get(0);
        var req = (HttpServletRequest) request;
        var reqUri = req.getRequestURI();

        // if Profile is local, use a test user
        if (currentProfile.equals("local")) {
            var user = environment.getProperty("TEST_USER");
            req.setAttribute("username", user);
        }

        // If the request is not to the API or to get static content
        // just continue the filtering process
        if (reqUri.startsWith("/api") || reqUri.startsWith("/built")) {
            chain.doFilter(request, response);
            return;
        }



        request.getRequestDispatcher("/").forward(request, response);
    }

    public static String getTokenFromCookie(HttpServletRequest req, String tokenName) {
        Cookie[] cookies = req.getCookies();

        if (cookies == null) {
            return null;
        }

        // Find the cookie with the cookie name for the JWT token
        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(tokenName)) {
                return cookie.getValue();
            }
        }

        return null;
    }

    public static String getUserFromToken(String token, String claim) throws ParseException {
        var parsetJwt = JWTParser.parse(token);
        String user = (String) parsetJwt.getJWTClaimsSet().getClaim(claim);

        return user;
    }
}
