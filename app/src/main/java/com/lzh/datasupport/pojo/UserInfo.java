package com.lzh.datasupport.pojo;

import com.lzh.datasupport.support.rules.RangeInt;
import com.lzh.datasupport.support.rules.RangeSize;
import com.lzh.datasupport.support.ChineseName;

public class UserInfo{

    @RangeInt(min = 0, max = 100)
    public int age;

    @ChineseName
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
