package com.dashdocnow.middleware;

import com.dashdocnow.utils.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Calendar;

@Aspect
@Component
public class FilterExceptionHandler extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (RuntimeException | IOException | ServletException ex) {

            ErrorResponse err = new ErrorResponse(
                    HttpStatus.BAD_REQUEST.value(),
                    ex.getMessage(),
                    Calendar.getInstance()
            );
            // setting response as Json
            // Makes it readable by Javascript
            String json = new ObjectMapper().writeValueAsString(err);
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write(json);
            response.flushBuffer();
        }
    }
}
