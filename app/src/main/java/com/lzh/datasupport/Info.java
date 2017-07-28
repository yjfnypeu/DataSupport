package com.lzh.datasupport;

import com.lzh.datasupport.core.annotation.NonNull;
import com.lzh.datasupport.core.annotation.RangeInt;
import com.lzh.datasupport.core.annotation.RangeSize;

/**
 * Created by haoge on 2017/7/27.
 */

public class Info {

    @RangeInt(min = 0, max = 100)
    int age;

    @RangeSize
    String username;

    @NonNull
    Info info;
}
