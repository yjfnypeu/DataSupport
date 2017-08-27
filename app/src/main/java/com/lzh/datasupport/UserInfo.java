package com.lzh.datasupport;

import com.lzh.datasupport.core.annotation.RangeInt;
import com.lzh.datasupport.core.annotation.RangeSize;
import com.lzh.datasupport.core.annotation.Requires;

/**
 * Created by haoge on 2017/7/27.
 */

public class UserInfo{

    @RangeInt(min = 0, max = 100)
    int age;

    @RangeSize
    String username;

    @Requires
    Info info;

    @Override
    public String toString() {
        return "UserInfo{" +
                "age=" + age +
                ", username='" + username + '\'' +
                ", info=" + info +
                '}';
    }
}
