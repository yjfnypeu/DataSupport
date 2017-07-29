package com.lzh.datasupport.pojo;

import com.lzh.datasupport.core.annotation.Requires;

/**
 * Created by haoge on 2017/7/29.
 */

public class Cyclic {

    @Requires
    public Cyclic sub;
}
