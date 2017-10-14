package com.lzh.datasupport.rules;

import com.lzh.datasupport.core.annotation.Checker;
import com.lzh.datasupport.core.annotation.Mocker;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Checker(ChineseNameSupport.class)
@Mocker(ChineseNameSupport.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ChineseName {}
