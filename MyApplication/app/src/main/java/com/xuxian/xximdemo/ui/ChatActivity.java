package com.xuxian.xximdemo.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.xuxian.xximdemo.R;
import com.xuxian.xximdemo.bean.XXMessage;
import com.xuxian.xximdemo.core.XXConnection;
import com.xuxian.xximdemo.listener.MessageReceiveListener;
import com.xuxian.xximdemo.util.XXConnectionHelper;

/**
 * 类描述：聊天界面
 * 创建人：quzongyang
 * 创建时间：2016/8/5. 16:53
 * 版本：
 */
public class ChatActivity extends AppCompatActivity {

    private TextView tv_chat;
    private Button btn_send;
    private EditText edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        tv_chat = (TextView) findViewById(R.id.tv_chat);
        btn_send = (Button) findViewById(R.id.btn_send);
        edit = (EditText) findViewById(R.id.edit);
        XXConnectionHelper.addMessageReceiveListener(messageReceiveListener);
        //XXConnection.getInstance().addMessageReceiveListener(messageReceiveListener);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edit.getText().toString();
                //XXConnection.getInstance().sendMessage(new XXMessage("", "", "", text, "", ""));
                XXConnectionHelper.sendMessage(new XXMessage("", "", "", text, "", ""));
            }
        });
    }

    private MessageReceiveListener messageReceiveListener = new MessageReceiveListener() {
        @Override
        public void onMessageReceive(final String msg) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    tv_chat.append("\n" + msg);
                }
            });
        }
    };
}
