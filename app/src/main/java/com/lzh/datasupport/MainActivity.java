package com.lzh.datasupport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.lzh.compiler.parceler.Parceler;
import com.lzh.datasupport.pojo.UserInfo;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickInvalidParams(View view) {
        Intent intent = new Intent(this, CheckActivity.class);
        startActivity(intent);
    }

    public void onClickValidParams(View view) {
        Intent intent = new Intent(this, CheckActivity.class);
        intent.putExtra("name", "haoge");
        startActivity(intent);
    }

    public void onClickMockParams(View view) {
        Intent intent = new Intent(this, CheckActivity.class);
        UserInfo info = DataSupport.getDefault().throwable(false).mock(UserInfo.class);
        Bundle bundle = Parceler.createFactory(new Bundle())
                .put("info", info)
                .put("name", info.username)
                .getBundle();
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
