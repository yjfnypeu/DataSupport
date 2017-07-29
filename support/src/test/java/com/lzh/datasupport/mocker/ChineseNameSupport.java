package com.lzh.datasupport.mocker;


import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.reflect.Field;
import java.util.Random;

public class ChineseNameSupport implements IMock<String, ChineseName>, ICheck<String, ChineseName>{
    private Random random = new Random();
    private final String[] XING = {"张", "孙", "李", "王", "刘"};
    private final String[] MING = {"一","二","三","四","五","六", "麻子"};

    @Override
    public String mock(ChineseName rule, Field field) throws Exception {
        return XING[random.nextInt(XING.length)] +
                MING[random.nextInt(MING.length)];
    }

    @Override
    public boolean check(String s, ChineseName rule) throws Exception {
        // 中文名最长为4
        return s.length() <= 4;
    }
}
