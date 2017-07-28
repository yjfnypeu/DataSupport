package com.lzh.datasupport.core.check;

import java.lang.annotation.Annotation;


public interface ICheck<T, A extends Annotation> {

    boolean check(T t, A rule) throws Exception;
}
