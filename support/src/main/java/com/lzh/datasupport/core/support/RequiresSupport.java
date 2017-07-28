package com.lzh.datasupport.core.support;

import com.lzh.datasupport.DataSupport;
import com.lzh.datasupport.core.annotation.Requires;
import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.reflect.Field;

public class RequiresSupport implements ICheck<Object, Requires>, IMock<Object, Requires>{
    @Override
    public boolean check(Object o, Requires rule) throws Exception {
        return DataSupport.create().throwable(false).check(o);
    }

    @Override
    public Object mock(Requires rule, Field field) throws Exception {
        return DataSupport.create().mock(field.getType());
    }
}
