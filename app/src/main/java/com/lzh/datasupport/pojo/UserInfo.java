package com.lzh.datasupport.pojo;

import com.lzh.datasupport.core.annotation.Mocker;
import com.lzh.datasupport.core.annotation.RangeInt;
import com.lzh.datasupport.core.annotation.RangeSize;
import com.lzh.datasupport.core.annotation.Requires;
import com.lzh.datasupport.mocker.NameMocker;

public class UserInfo{

    @RangeInt(min = 0, max = 100)
    public int age;

    @Mocker(NameMocker.class)// 对username设置模拟规则。
    @RangeSize(3)
    public String username;

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", username='" + username + '\'' +
                '}';
    }


}
