package com.lzh.datasupport.mocker;

import com.lzh.datasupport.core.annotation.RangeSize;
import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Random;

/**
 * 模拟创建姓名：
 */
public class NameMocker implements IMock<String, Annotation> {

    private Random random = new Random();
    private final String[] XING = {"张", "孙", "李", "王", "刘"};
    private final String[] MING = {"一","二","三","四","五","六", "麻子"};

    @Override
    public String mock(Annotation rule, Field field) throws Exception {
        return XING[random.nextInt(XING.length)] +
                MING[random.nextInt(MING.length)];
    }
}
