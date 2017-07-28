package com.lzh.datasupport.core.mock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface IMock<T,A extends Annotation> {

    T mock(A rule, Field field) throws Exception;
}
