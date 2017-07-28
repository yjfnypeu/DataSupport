package com.lzh.datasupport.core.annotation;

import com.lzh.datasupport.core.support.RequiresSupport;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Checker(RequiresSupport.class)
@Mocker(RequiresSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Requires {}
