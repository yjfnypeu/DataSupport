package com.lzh.datasupport;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lzh.datasupport.pojo.UserInfo;

public class MainActivity extends Activity {

    private UserInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onMockInfo(View view) {
        info = DataSupport.create().mock(UserInfo.class);
        Toast.makeText(this, "mock data：" + info, Toast.LENGTH_SHORT).show();
    }

    public void onCheckInfo(View view) {
        boolean check = DataSupport.create().check(info);
        Toast.makeText(this, "check info: " + check, Toast.LENGTH_SHORT).show();
    }
}
