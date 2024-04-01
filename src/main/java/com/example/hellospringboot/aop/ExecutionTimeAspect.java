package com.example.hellospringboot.aop;


import com.example.hellospringboot.annotation.MeasureExecutionTime;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class ExecutionTimeAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    @Before("@annotation(com.example.hellospringboot.annotation.MeasureExecutionTime)")
    public void logMethodAccessBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("Before execution of [{}.{}]", className, methodName);
    }

    @Around("@annotation(com.example.hellospringboot.annotation.MeasureExecutionTime)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long endTime = System.currentTimeMillis();

        logger.info("Execution time of {} : {} ms",
                joinPoint.getSignature().toShortString(),
                endTime - startTime);

        return result;
    }

    @After("@annotation(com.example.hellospringboot.annotation.MeasureExecutionTime)")
    public void logMethodAccessAfter(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("After execution of [{}.{}]", className, methodName);
    }
    @AfterReturning(pointcut = "@annotation(com.example.hellospringboot.annotation.MeasureExecutionTime)",
            returning = "retVal")
    public void logMethodAccessAfterReturning(JoinPoint joinPoint, Object retVal) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.info("After returning from [{}.{}] with return value: {}", className, methodName, retVal);
    }

    @AfterThrowing(pointcut = "@annotation(com.example.hellospringboot.annotation.MeasureExecutionTime)",
            throwing = "ex")
    public void logMethodAccessAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getSignature().getDeclaringTypeName();
        logger.error("After throwing from [{}.{}] with exception: {}", className, methodName, ex.getMessage());
    }
}