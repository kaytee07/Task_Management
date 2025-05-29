package com.project.task_management.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("execution(public * com.project.task_management.service.UserService.createUser())")
    public void logBeforeMethod(JoinPoint joinPoint) {
        logger.info("Creating new user");
    }

    @AfterReturning(pointcut = "execution(public * com.project.task_management.service.UserService.createUser()))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Successfully created a new user ");
    }
}

