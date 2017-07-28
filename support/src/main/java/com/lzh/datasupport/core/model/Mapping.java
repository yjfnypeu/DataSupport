package com.lzh.datasupport.core.model;

import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class Mapping {
    public IMock mock;
    public ICheck[] checks;
    public Annotation annotation;
    public Field field;
}
