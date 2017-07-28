package com.lzh.datasupport.core.annotation;

import com.lzh.datasupport.core.check.ICheck;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Checker {
    Class<? extends ICheck>[] value();
}
