package com.epam.esm.aspect;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceAspect {
    private static final Logger logger = LogManager.getLogger();

    @Pointcut("execution(int com.epam.esm.*..*(..))")
    public void methodReturnIntPointcut() {}

    @AfterReturning(pointcut = "methodReturnIntPointcut()", returning = "result")
    public void logMethodReturnInt(JoinPoint joinPoint, int result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();

        logger.log(Level.INFO, "{} method return {}", methodName, result);
    }
}
