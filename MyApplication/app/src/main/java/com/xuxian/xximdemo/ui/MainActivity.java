package com.xuxian.xximdemo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xuxian.xximdemo.R;
import com.xuxian.xximdemo.core.XXConnection;
import com.xuxian.xximdemo.global.BaseApplication;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/5  14:14
 *
 * 描 述 :主页面
 *
 * 修订日期 :
 */
public class MainActivity extends AppCompatActivity {

    Button btn_sign_in_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_sign_in_button = (Button) findViewById(R.id.btn_sign_in_button);
        btn_sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XXConnection xxConnection = XXConnection.getInstance();
                xxConnection.open();
                BaseApplication application = (BaseApplication) getApplication();
                application.setLongConn(xxConnection);
                startActivity(new Intent(MainActivity.this,ChatActivity.class));
            }
        });


    }
}
