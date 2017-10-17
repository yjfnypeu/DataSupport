package com.lzh.datasupport;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.lzh.compiler.parceler.Parceler;
import com.lzh.compiler.parceler.annotation.Arg;
import com.lzh.datasupport.support.rules.NonNull;
import com.lzh.datasupport.support.rules.Requires;
import com.lzh.datasupport.pojo.UserInfo;

public class CheckActivity extends Activity {

    // 指定要求执行此类型的内部检查规则,指定nullable为true。代表当此变量的值为null时，将跳过此类型的内部检查规则。
    @Requires(nullable = true)
    @Arg
    UserInfo info;

    // 指定此字段不能为null。
    @NonNull
    @Arg
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_params);
        // 读取注入数据
        Parceler.toEntity(this, getIntent());
        // 进行检查
        if (!DataSupport.getDefault().throwable(false).check(this)) {
            Toast.makeText(this, "检查失败", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        TextView nameTv = (TextView) findViewById(R.id.name);
        nameTv.setText(name);

        TextView infoTv = (TextView) findViewById(R.id.info);
        if (info == null) {
            infoTv.setText("没有用户信息");
        } else {
            infoTv.setText("用户信息：name = " + info.username + ", and age = " + info.age);
        }

    }


}
