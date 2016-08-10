package com.xuxian.xximdemo.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.xuxian.xximdemo.R;
import com.xuxian.xximdemo.adapter.ChatMsgViewAdapter;
import com.xuxian.xximdemo.base.BaseActivity;
import com.xuxian.xximdemo.bean.MessageBean;
import com.xuxian.xximdemo.bean.TextMessageBody;
import com.xuxian.xximdemo.bean.XXMessage;
import com.xuxian.xximdemo.core.XXConnection;
import com.xuxian.xximdemo.listener.MessageReceiveListener;
import com.xuxian.xximdemo.util.JsonHelper;
import com.xuxian.xximdemo.util.SoundMeter;
import com.xuxian.xximdemo.util.XXConnectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 类描述：聊天界面
 * 创建人：quzongyang
 * 创建时间：2016/8/5. 16:53
 * 版本：
 */
public class ChatActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnSend;
    private TextView mBtnRcd;
    private Button mBtnBack;
    private EditText mEditTextContent;
    private RelativeLayout mBottom;
    private ListView mListView;
    private ChatMsgViewAdapter mAdapter;
    private List<XXMessage> mDataArrays = new ArrayList<XXMessage>();
    private boolean isShosrt = false;
    private LinearLayout voice_rcd_hint_loading, voice_rcd_hint_rcding,
            voice_rcd_hint_tooshort;
    private ImageView img1, sc_img1;
    private SoundMeter mSensor;
    private View rcChat_popup;
    private LinearLayout del_re;
    private ImageView chatting_mode_btn, volume;
    private boolean btn_vocie = false;
    private int flag = 1;
    private Handler mHandler = new Handler();
    private String voiceName;
    private long startVoiceT, endVoiceT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        // 启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        initView();
        initData();
        XXConnectionHelper.addMessageReceiveListener(messageReceiveListener);

//        XXConnectionHelper.addMessageReceiveListener(messageReceiveListener);
//        //XXConnection.getInstance().addMessageReceiveListener(messageReceiveListener);
//        btn_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String text = edit.getText().toString();
//                edit.setText("");
//                //XXConnection.getInstance().sendMessage(new XXMessage("", "", "", text, "", ""));
//                XXConnectionHelper.sendMessage(new XXMessage("", "", "", text, "", ""));
//            }
//        });
    }

    public void initData() {
        mAdapter = new ChatMsgViewAdapter(this, mDataArrays);
        mListView.setAdapter(mAdapter);

    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn_send:
                send();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void send() {
        String contString = mEditTextContent.getText().toString();
        if (contString.length() > 0) {

            XXMessage message = new XXMessage("", "", "", new TextMessageBody(contString), "", "");
            mDataArrays.add(message);
            XXConnectionHelper.sendMessage(message);
            mAdapter.notifyDataSetChanged();
            mEditTextContent.setText("");
            mListView.setSelection(mListView.getCount() - 1);
        }else{
            Toast.makeText(ChatActivity.this,"不能发送空",Toast.LENGTH_SHORT).show();
        }
    }


    public void initView() {
        mListView = (ListView) findViewById(R.id.listview);
        mBtnSend = (Button) findViewById(R.id.btn_send);
        mBtnRcd = (TextView) findViewById(R.id.btn_rcd);
        mBtnSend.setOnClickListener(this);
        mBtnBack = (Button) findViewById(R.id.btn_back);
        mBottom = (RelativeLayout) findViewById(R.id.btn_bottom);
        mBtnBack.setOnClickListener(this);
        chatting_mode_btn = (ImageView) this.findViewById(R.id.ivPopUp);
        volume = (ImageView) this.findViewById(R.id.volume);
        rcChat_popup = this.findViewById(R.id.rcChat_popup);
        img1 = (ImageView) this.findViewById(R.id.img1);
        sc_img1 = (ImageView) this.findViewById(R.id.sc_img1);
        del_re = (LinearLayout) this.findViewById(R.id.del_re);
        voice_rcd_hint_rcding = (LinearLayout) this
                .findViewById(R.id.voice_rcd_hint_rcding);
        voice_rcd_hint_loading = (LinearLayout) this
                .findViewById(R.id.voice_rcd_hint_loading);
        voice_rcd_hint_tooshort = (LinearLayout) this
                .findViewById(R.id.voice_rcd_hint_tooshort);
        mSensor = new SoundMeter();
        mEditTextContent = (EditText) findViewById(R.id.et_sendmessage);

        //语音文字切换按钮
        chatting_mode_btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                if (btn_vocie) {
                    mBtnRcd.setVisibility(View.GONE);
                    mBottom.setVisibility(View.VISIBLE);
                    btn_vocie = false;
                    chatting_mode_btn
                            .setImageResource(R.drawable.chatting_setmode_msg_btn);

                } else {
                    mBtnRcd.setVisibility(View.VISIBLE);
                    mBottom.setVisibility(View.GONE);
                    chatting_mode_btn
                            .setImageResource(R.drawable.chatting_setmode_voice_btn);
                    btn_vocie = true;
                }
            }
        });
        mBtnRcd.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                //按下语音录制按钮时返回false执行父类OnTouch
                return false;
            }
        });
    }

    private MessageReceiveListener messageReceiveListener = new MessageReceiveListener() {
        @Override
        public void onMessageReceive(final String msg) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    XXMessage message = JsonHelper.parseObjectByJsonStr(msg, XXMessage.class);
                    if(message != null){
                        mDataArrays.add(message);
                        mAdapter.notifyDataSetChanged();
                        mEditTextContent.setText("");
                        mListView.setSelection(mListView.getCount() - 1);
                    }
                    //tv_chat.append("\n" + msg);
                }
            });
        }
    };



    /*private TextView tv_chat;
    private Button btn_send;
    private EditText edit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_activity);
        tv_chat = (TextView) findViewById(R.id.tv_chat);
        btn_send = (Button) findViewById(R.id.btn_send);
        edit = (EditText) findViewById(R.id.edit);
        XXConnectionHelper.addMessageReceiveListener(messageReceiveListener);
        //XXConnection.getInstance().addMessageReceiveListener(messageReceiveListener);
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = edit.getText().toString();
                edit.setText("");
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
    };*/
}
