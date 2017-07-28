package com.lzh.datasupport;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserInfo mock = DataSupport.create().mock(UserInfo.class);
        DataSupport.create().check(mock);
    }
}
