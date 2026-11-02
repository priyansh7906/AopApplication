package com.example.AopApplication.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Component
//@Aspect

public class LoggingAspect {

//    @Before("execution(* orderPackage(..))")
//    @Before("execution(* com.example.AopApplication.Services.impl.ShipmentServiceImpl.*(..))")
@Before("execution(* com.example.AopApplication.Services.impl.*.*(..))")
    public void beforeShipmentServiceMethod(JoinPoint joinPoint) {

        log.info("Before Shipment Service called full package : {}", joinPoint.getSignature());
    log.info("Before called from loggingAspect kind : {}", joinPoint.getKind());
    log.info("Before Method name call : {}", joinPoint.getSignature().getName());
    }

    @After("myLoggingAndMethodAspectPointcut()")
    public void  AfterServiceMethodTransactional() {
        log.info("After My logging call");
    }

    @Before("within(com.example.AopApplication.Services.impl.*)")
    public void beforeShipmentServiceMethodWithin(JoinPoint joinPoint) {
    log.info("service implementation call");
    }

    @Before("myLoggingAndMethodAspectPointcut()")
    public void beforeShipmentServiceMethodTransactional() {
    log.info("before Mylogging call");
    }

    @Pointcut( "@annotation(com.example.AopApplication.aspect.Mylogging) && " +
            "within(com.example.AopApplication.Services.impl.*)")
    public void myLoggingAndMethodAspectPointcut()
    {
    }

}
