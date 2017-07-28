package com.lzh.datasupport.core.support;

import com.lzh.datasupport.core.annotation.RangeSize;
import com.lzh.datasupport.core.check.ICheck;

import java.util.Collection;
import java.util.Map;

public class RangeSizeSupport implements ICheck<Object, RangeSize>{

    @Override
    public boolean check(Object o, RangeSize rule) throws Exception {
        int size = rule.value();
        if (o == null) {
            return false;
        } else if (o instanceof CharSequence) {
            return size >= ((CharSequence) o).length();
        } else if (o instanceof Collection) {
            return size >= ((Collection) o).size();
        } else if (o instanceof Map) {
            return size >= ((Map) o).size();
        }
        throw new RuntimeException(String.format("Unsupported type: %s", o.getClass()));
    }
}
