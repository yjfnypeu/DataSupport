package com.lzh.datasupport.core.annotation;

import com.lzh.datasupport.core.support.RangeIntSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Checker(RangeIntSupport.class)
@Mocker(RangeIntSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RangeInt {
    int min() default Integer.MIN_VALUE;
    int max() default Integer.MAX_VALUE;
}
