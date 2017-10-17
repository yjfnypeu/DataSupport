package com.lzh.datasupport.support;

import com.lzh.datasupport.core.annotation.Checker;
import com.lzh.datasupport.core.annotation.Mocker;
import com.lzh.datasupport.core.check.ICheck;
import com.lzh.datasupport.core.mock.IMock;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.Random;


@Checker(ChineseName.NameSupport.class)
@Mocker(ChineseName.NameSupport.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChineseName {
    /**
     * 用于对中文名进行模拟检查
     * @author haoge on 2017/10/16.
     */
    class NameSupport implements ICheck<String, Annotation>, IMock<String, Annotation> {
        private Random random = new Random();
        private final String[] XING = {"张", "孙", "李", "王", "刘"};
        private final String[] MING = {"一","二","三","四","五","六", "麻子"};

        @Override
        public boolean check(String data, Annotation rule) throws Exception {
            return data != null && data.length() <= 3;
        }

        @Override
        public String mock(Annotation rule, Field field) throws Exception {
            return XING[random.nextInt(XING.length)] +
                    MING[random.nextInt(MING.length)];
        }
    }
}


