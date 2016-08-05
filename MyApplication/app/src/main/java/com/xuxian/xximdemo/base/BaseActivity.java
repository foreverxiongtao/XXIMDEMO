package com.xuxian.xximdemo.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.xuxian.xximdemo.util.AppManager;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/8/5. 16:58
 * 版本：
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppManager.getInstance().addActivity(this);
    }

    @Override
    protected void onDestroy() {
        AppManager.getInstance().killActivity(this);
        super.onDestroy();
    }

}
