package com.xuxian.xximdemo.service;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.xuxian.xximdemo.R;
import com.xuxian.xximdemo.global.BaseApplication;
import com.xuxian.xximdemo.listener.MessageReceiveListener;
import com.xuxian.xximdemo.ui.ChatActivity;
import com.xuxian.xximdemo.util.ConnectionRegisterException;
import com.xuxian.xximdemo.util.XXConnectionHelper;

import java.util.List;

/**
 * 类描述：IM服务
 * 创建人：quzongyang
 * 创建时间：2016/8/8. 10:03
 * 版本：
 */
public class WebSocketService extends Service {
    private BaseApplication application;
    private MessageReceiveListener messageReceiveListener = new MessageReceiveListener() {
        @Override
        public void onMessageReceive(String msg) {
            //获得活动管理器
            ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
            //获取当前互动栈顶的活动信息
            List<ActivityManager.RunningTaskInfo> runningTaskInfos = manager.getRunningTasks(1);
            String taskTop = null;
            if (runningTaskInfos != null) {
                taskTop = runningTaskInfos.get(0).topActivity.getShortClassName();
            }
            Log.e("wow", taskTop + "");
            //如果不是聊天活动则发送一条通知
            if (!taskTop.equals(".ui.ChatActivity")) {
                Log.e("wow", "启动通知");
                //构建一条通知
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(WebSocketService.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("你有一条新信息")
                                .setContentText(msg + "")
                                .setWhen(System.currentTimeMillis())
                                .setDefaults(NotificationCompat.DEFAULT_ALL)
                                .setAutoCancel(true);
                // 创建意图
                Intent resultIntent = new Intent(WebSocketService.this, ChatActivity.class);
                long notifiTime = System.currentTimeMillis();
                resultIntent.putExtra("notifiContent", "你有一条新信息");
                resultIntent.putExtra("notifiTime", notifiTime);
                // 通过TaskStackBuilder创建PendingIntent对象
                TaskStackBuilder stackBuilder = TaskStackBuilder.create(WebSocketService.this);
                stackBuilder.addParentStack(ChatActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                1,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);

                //获取通知管理器
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                //发送通知
                mNotificationManager.notify(1, mBuilder.build());
            }
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            XXConnectionHelper.registerService();
            XXConnectionHelper.addMessageReceiveListener(messageReceiveListener);
        } catch (ConnectionRegisterException _e) {
            _e.printStackTrace();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (messageReceiveListener != null) {
            XXConnectionHelper.removeMessageReceiveListener(messageReceiveListener);
            messageReceiveListener = null;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
