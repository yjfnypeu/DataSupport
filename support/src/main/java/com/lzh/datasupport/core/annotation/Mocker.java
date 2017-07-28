package com.lzh.datasupport.core.annotation;

import com.lzh.datasupport.core.mock.IMock;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Mocker {

    Class<? extends IMock> value();
}
