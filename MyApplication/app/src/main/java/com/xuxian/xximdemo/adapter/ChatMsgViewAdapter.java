package com.xuxian.xximdemo.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xuxian.xximdemo.R;
import com.xuxian.xximdemo.bean.TextMessageBody;
import com.xuxian.xximdemo.bean.XXMessage;

import java.util.List;

/**
 * 类描述：聊天数据源
 * 创建人：quzongyang
 * 创建时间：2016/8/10. 15:08
 * 版本：
 */
public class ChatMsgViewAdapter extends BaseAdapter {

    public static interface IMsgViewType {
        int IMVT_COM_MSG = 0;
        int IMVT_TO_MSG = 1;
    }

    private static final String TAG = ChatMsgViewAdapter.class.getSimpleName();

    private List<XXMessage> coll;

    private Context mContext;

    private LayoutInflater mInflater;
    private MediaPlayer mMediaPlayer = new MediaPlayer();

    public ChatMsgViewAdapter(Context context, List<XXMessage> coll) {
        mContext = context;
        this.coll = coll;
        mInflater = LayoutInflater.from(context);
    }

    public int getCount() {
        if(null != coll && coll.size()>0){
            return coll.size();
        }else{
            return 0;
        }
    }

    public Object getItem(int position) {
        return coll.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public int getItemViewType(int position) {
        // TODO Auto-generated method stub
        /*ChatMsgEntity entity = coll.get(position);

        if (entity.getMsgType()) {
            return IMsgViewType.IMVT_COM_MSG;
        } else {
            return IMsgViewType.IMVT_TO_MSG;
        }*/
        return 0;

    }


    public View getView(int position, View convertView, ViewGroup parent) {

        final XXMessage entity = coll.get(position);
        final TextMessageBody msgBody = (TextMessageBody) entity.getMessageBody();
        //boolean isComMsg = entity.getMsgType();

        ViewHolder viewHolder = null;
        if (convertView == null) {
            /*if (isComMsg) {
                convertView = mInflater.inflate(
                        R.layout.chatting_item_msg_text_left, null);
            } else {
                convertView = mInflater.inflate(
                        R.layout.chatting_item_msg_text_right, null);
            }*/
            convertView = mInflater.inflate(
                    R.layout.chatting_item_msg_text_left, null);
            viewHolder = new ViewHolder();
            viewHolder.tvSendTime = (TextView) convertView
                    .findViewById(R.id.tv_sendtime);
            viewHolder.tvUserName = (TextView) convertView
                    .findViewById(R.id.tv_username);
            viewHolder.tvContent = (TextView) convertView
                    .findViewById(R.id.tv_chatcontent);
            viewHolder.tvTime = (TextView) convertView
                    .findViewById(R.id.tv_time);
            //viewHolder.isComMsg = isComMsg;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvSendTime.setText(entity.getTime());

        if(msgBody != null){
            if (msgBody.getContent().contains(".amr")) {
                viewHolder.tvContent.setText("");
                viewHolder.tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.chatto_voice_playing, 0);
                viewHolder.tvTime.setText(entity.getTime());
            } else {
                viewHolder.tvContent.setText(msgBody.getContent());
                viewHolder.tvContent.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                viewHolder.tvTime.setText("");
            }
            viewHolder.tvContent.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    if (msgBody.getContent().contains(".amr")) {
                        playMusic(android.os.Environment.getExternalStorageDirectory()+"/"+msgBody.getContent()) ;
                    }
                }
            });
        }

        //viewHolder.tvUserName.setText(entity.getName());

        return convertView;
    }

    static class ViewHolder {
        public TextView tvSendTime;
        public TextView tvUserName;
        public TextView tvContent;
        public TextView tvTime;
        public boolean isComMsg = true;
    }

    /**
     * @Description
     * @param name
     */
    private void playMusic(String name) {
        try {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.stop();
            }
            mMediaPlayer.reset();
            mMediaPlayer.setDataSource(name);
            mMediaPlayer.prepare();
            mMediaPlayer.start();
            mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {

                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void stop() {

    }

}
