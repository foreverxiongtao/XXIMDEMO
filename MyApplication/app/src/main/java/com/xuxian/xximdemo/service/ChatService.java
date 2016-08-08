/*
package com.xuxian.xximdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xuxian.xximdemo.core.XXConnection;
import com.xuxian.xximdemo.global.BaseApplication;
import com.xuxian.xximdemo.util.AppManager;

*/
/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/5  14:28
 *
 * 描 述 :聊天服务
 *
 * 修订日期 :
 *//*

public class ChatService extends Service {


    private BaseApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = (BaseApplication) getApplication();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(AppManager.getInstance().ActivityStackIsEmpty()){
            if(application == null || application.getLongConn()== null){
                XXConnection.getInstance().open();
            }
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
*/
