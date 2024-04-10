package com.dashdocapi.middleware;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.dashdocapi.services.*.save(..))")
    private void saveOperation() {}
    @Pointcut("execution(* com.dashdocapi.services.*.getById(..))")
    private void getByIdOperation() {}
    @Pointcut("execution(* com.dashdocapi.services.*.update(..))")
    private void updateOperation() {}
    @Pointcut("execution(* com.dashdocapi.services.*.delete(..))")
    private void deleteOperation() {}

    @After("saveOperation()")
    public void afterSaveAdvice(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getSignature());
    }

    @After("getByIdOperation()")
    public void afterGetByIdAdvice(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getSignature());
    }

    @After("updateOperation()")
    public void afterUpdateAdvice(JoinPoint joinPoint){
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getSignature());
    }

    @After("deleteOperation()")
    public void afterDeleteAdvice(JoinPoint joinPoint) {
        System.out.println(joinPoint.getSignature());
        System.out.println(joinPoint.getTarget());
        System.out.println(joinPoint.getSignature());
    }
}
