package com.lzh.datasupport.core.support;

import com.lzh.datasupport.core.annotation.RangeInt;
import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by haoge on 2017/7/28.
 */

public class RangeIntSupport implements ICheck<Integer, RangeInt>, IMock<Integer, RangeInt>{

    private Random random = new Random();

    @Override
    public Integer mock(RangeInt rule, Field field) throws Exception {
        int min = rule.min();
        int max = rule.max();
        return random.nextInt(max) - min;
    }

    @Override
    public boolean check(Integer integer, RangeInt rule) throws Exception {
        int min = rule.min();
        int max = rule.max();
        return integer != null && integer > min && integer < max;
    }
}
