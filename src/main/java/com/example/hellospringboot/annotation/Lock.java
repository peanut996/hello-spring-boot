package com.example.hellospringboot.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lock {
    
    String key() default "";
    
    long waitTime() default 5000;
    
    long leaseTime() default -1;
    
    String keyPrefix() default "lock:";
}