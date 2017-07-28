package com.lzh.datasupport.core.annotation;

import com.lzh.datasupport.core.support.RangeSizeSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Checker(RangeSizeSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RangeSize {
    int value() default 0;
}
