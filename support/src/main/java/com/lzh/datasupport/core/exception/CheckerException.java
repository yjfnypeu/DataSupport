package com.lzh.datasupport.core.exception;

import java.lang.annotation.Annotation;

/**
 * 框架所特意提取出来的定制异常
 */
public class CheckerException extends RuntimeException{

    private Object entity;
    private Object field;
    private Annotation annotation;

    public CheckerException(String s, Object entity, Object field, Annotation annotation) {
        super(s);
        this.entity = entity;
        this.field = field;
        this.annotation = annotation;
    }
}
