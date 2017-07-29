package com.lzh.datasupport.pojo;

import com.lzh.datasupport.core.annotation.Mocker;
import com.lzh.datasupport.core.annotation.NonNull;
import com.lzh.datasupport.core.annotation.RangeInt;
import com.lzh.datasupport.core.annotation.RangeSize;
import com.lzh.datasupport.mocker.ChineseName;
import com.lzh.datasupport.mocker.NameMocker;

public class Simple {
    // 指定取值范围为[-10, 10]
    @RangeInt(min = -10, max = 10)
    public int range;

    @Mocker(NameMocker.class)
    @RangeSize(3)
    public String name;

    @ChineseName
    public String names;

    @Override
    public String toString() {
        return "Simple{" +
                "range=" + range +
                ", name='" + name + '\'' +
                ", names='" + names + '\'' +
                '}';
    }
}
