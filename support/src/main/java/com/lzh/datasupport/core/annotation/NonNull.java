package com.lzh.datasupport.core.annotation;

import com.lzh.datasupport.core.support.NonNullSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Checker(NonNullSupport.class)
@Mocker(NonNullSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NonNull {
}
