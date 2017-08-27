package com.lzh.datasupport.core.exception;

import java.lang.annotation.Annotation;

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
