package com.lzh.datasupport;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    private UserInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserInfo mock = DataSupport.create().mock(UserInfo.class);
        System.out.println("mock data:" + mock);
        System.out.println("check mock data:" + DataSupport.create().check(mock));
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
