package com.example.AopApplication.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspectV2 {


    @Before("AllServiceMethodPointcut()")
    public void beforeServiceMethodCall(JoinPoint joinPoint) {
        log.info("Before Advice Method Call, {}", joinPoint.getSignature());
    }


//    @After("AllServiceMethodPointcut()")
    @AfterReturning(value = "AllServiceMethodPointcut()", returning = "returnObj")
    public void AfterServiceMethodCall(JoinPoint joinPoint, Object returnObj) {
        log.info("After Returning all Advice Method Call, {}", joinPoint.getSignature());
        log.info("After Returning Return value , {}", returnObj.toString());
    }


    @AfterThrowing("AllServiceMethodPointcut()")
    public void AfterThrowingServiceMethodCall(JoinPoint joinPoint) {
        log.info("After Throwing  Advice Method Call, {}", joinPoint.getSignature());

    }

    @Around("AllServiceMethodPointcut()")
    public Object logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Long startTime = System.currentTimeMillis();
        Object returnValue=  proceedingJoinPoint.proceed();
        Long endTime = System.currentTimeMillis();

        Long executionTime = endTime - startTime;
        log.info("time taken for {} is {}", proceedingJoinPoint.getSignature(), executionTime);
        return returnValue;
    }



    @Pointcut("execution(* com.example.AopApplication.Services.impl.*.*(..))")
    public void AllServiceMethodPointcut() {}


}


