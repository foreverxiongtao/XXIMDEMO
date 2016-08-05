package com.xuxian.xximdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.xuxian.xximdemo.R;
import com.xuxian.xximdemo.global.BaseApplication;
import com.xuxian.xximdemo.listener.MessageReceiveListener;

/**
 * 类描述：聊天界面
 * 创建人：quzongyang
 * 创建时间：2016/8/5. 16:53
 * 版本：
 */
public class ChatActivity extends AppCompatActivity {

    private TextView tv_chat;
    private BaseApplication application;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tv_chat = (TextView) findViewById(R.id.tv_chat);

        application = (BaseApplication) getApplication();
        application.getLongConn().addMessageReceiveListener(messageReceiveListener);
    }

    private MessageReceiveListener messageReceiveListener = new MessageReceiveListener() {
        @Override
        public void onMessageReceive(String msg) {
            tv_chat.setText(msg);
        }
    };
}
