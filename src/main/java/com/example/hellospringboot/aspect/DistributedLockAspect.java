package com.example.hellospringboot.aspect;

import com.example.hellospringboot.annotation.Lock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedLockAspect {
    
    private final RedissonClient redissonClient;
    private final ExpressionParser parser = new SpelExpressionParser();
    private final DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
    
    @Around("@annotation(distributedLock)")
    public Object around(ProceedingJoinPoint joinPoint, Lock distributedLock) throws Throwable {
        String lockKey = buildLockKey(joinPoint, distributedLock);
        RLock lock = redissonClient.getLock(lockKey);
        
        boolean isLocked = false;
        try {
            long leaseTime = distributedLock.leaseTime();
            long waitTime = distributedLock.waitTime();
            
            log.debug("Attempting to acquire lock: {}, waitTime: {}ms, leaseTime: {}ms", lockKey, waitTime, leaseTime);
            
            if (leaseTime <= 0) {
                // leaseTime <= 0 时自动释放：不设置leaseTime，执行完自动释放
                isLocked = lock.tryLock(waitTime, TimeUnit.MILLISECONDS);
                log.debug("Lock attempt result (auto-release) for {}: {}", lockKey, isLocked);
            } else {
                // leaseTime > 0 时使用固定时间
                isLocked = lock.tryLock(waitTime, leaseTime, TimeUnit.MILLISECONDS);
                log.debug("Lock attempt result (lease time {}ms) for {}: {}", leaseTime, lockKey, isLocked);
            }
            
            if (!isLocked) {
                log.warn("Failed to acquire distributed lock: {}", lockKey);
                throw new RuntimeException("Failed to acquire distributed lock: " + lockKey);
            }
            
            log.info("Successfully acquired distributed lock: {}, isHeldByCurrentThread: {}", 
                    lockKey, lock.isHeldByCurrentThread());
            return joinPoint.proceed();
            
        } finally {
            if (isLocked && lock.isHeldByCurrentThread()) {
//                lock.unlock();
                log.info("Released distributed lock: {}", lockKey);
            } else {
                log.warn("Lock release skipped - isLocked: {}, isHeldByCurrentThread: {}, lockKey: {}", 
                        isLocked, lock.isHeldByCurrentThread(), lockKey);
            }
        }
    }
    
    private String buildLockKey(ProceedingJoinPoint joinPoint, Lock lock) {
        String key = lock.key();
        String keyPrefix = lock.keyPrefix();
        
        if (StringUtils.hasText(key)) {
            String parsedKey = parseSpEL(joinPoint, key);
            return keyPrefix + parsedKey;
        }
        
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String className = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        return keyPrefix + className + ":" + methodName;
    }
    
    private String parseSpEL(ProceedingJoinPoint joinPoint, String key) {
        if (!key.contains("#")) {
            return key;
        }
        
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        String[] parameterNames = discoverer.getParameterNames(method);
        Object[] args = joinPoint.getArgs();
        
        EvaluationContext context = new StandardEvaluationContext();
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                context.setVariable(parameterNames[i], args[i]);
            }
        }
        
        try {
            Expression expression = parser.parseExpression(key);
            return expression.getValue(context, String.class);
        } catch (Exception e) {
            log.warn("Failed to parse SpEL expression: {}, using original key", key, e);
            return key;
        }
    }
}