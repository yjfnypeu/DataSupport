package com.lzh.datasupport.core.support;

import com.lzh.datasupport.core.annotation.NonNull;
import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.reflect.Field;

public class NonNullSupport implements ICheck<Object, NonNull>, IMock<Object, NonNull> {

    @Override
    public Object mock(NonNull rule, Field field) throws Exception {
        return field.getType().newInstance();
    }

    @Override
    public boolean check(Object o, NonNull rule) throws Exception {
        return o != null;
    }
}
