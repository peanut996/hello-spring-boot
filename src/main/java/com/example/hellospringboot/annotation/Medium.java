package com.example.hellospringboot.annotation;

import com.example.hellospringboot.enumerate.Point;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target(value = {ElementType.TYPE, ElementType.METHOD})
public @interface Medium {
    Point[] point() default {};

    String source() default "";

    String title() default "";
}
