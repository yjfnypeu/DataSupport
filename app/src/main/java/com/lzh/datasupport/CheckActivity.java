package com.lzh.datasupport;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.lzh.compiler.parceler.annotation.NonNull;
import com.lzh.datasupport.core.annotation.Requires;
import com.lzh.datasupport.pojo.UserInfo;

public class CheckActivity extends Activity {

    // 指定要求执行此类型的内部检查规则
    @Requires
    UserInfo info;

    // 指定此字段不能为null。
    @NonNull
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_params);
        if (getIntent() == null) {
            return;
        }
        if (!DataSupport.getDefault().check(this)) {
            // 检查失败。销毁当前页
            Toast.makeText(this, "数据检查无效", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

}
