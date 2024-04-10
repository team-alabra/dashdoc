package com.dashdocapi.middleware;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Aspect
@Component
public class ValidationAspect {
    @Before("within(com.dashdocapi.controllers..*) && args(id)")
    public void idChecker(JoinPoint joinPoint, Long id) throws Exception {
        if (id < 1) {
            throw new Exception("invalid input id: " + id);
        }
    }

    @Before("within(com.dashdocapi.controllers..*) && args(email)")
    public void emailChecker(JoinPoint joinPoint, String email) throws Exception {
        // needs to be implemented
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
        boolean isValidEmail = Pattern.compile(regexPattern)
                .matcher(email)
                .matches();
        System.out.println(">>> running email validation");
    }
}
