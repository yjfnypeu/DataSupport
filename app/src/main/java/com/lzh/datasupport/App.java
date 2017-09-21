package com.lzh.datasupport;

import android.app.Application;

import com.lzh.compiler.parceler.Parceler;
import com.lzh.compiler.parceler.annotation.FastJsonConverter;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 设置默认数据转换器
        Parceler.setDefaultConverter(FastJsonConverter.class);
    }
}
