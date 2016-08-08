package com.xuxian.xximdemo.util;

import android.content.Context;
import android.content.Intent;

import com.xuxian.xximdemo.core.XXConnection;
import com.xuxian.xximdemo.listener.MessageReceiveListener;
import com.xuxian.xximdemo.service.WebSocketService;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/8  13:40
 *
 * 描 述 :连接通道工具类
 *
 * 修订日期 :
 */
public class XXConnectionHelper {

    /***
     * 开启服务
     *
     * @param _context
     * @throws Exception
     */
    public static void startService(Context _context) throws ConnectionRegisterException {
        if (!AppUtils.isServiceWork(_context, "com.xuxian.xximdemo.service.WebSocketService")) {
            _context.startService(new Intent(_context, WebSocketService.class));
        }
    }

    /***
     * 添加消息监听
     *
     * @param _listener
     */
    public static void addMessageReceiveListener(MessageReceiveListener _listener) {
        if (_listener != null) {
            XXConnection.getInstance().addMessageReceiveListener(_listener);
        }
    }

    /***
     * 移除消息监听
     *
     * @param _listener
     */
    public static void removeMessageReceiveListener(MessageReceiveListener _listener) {
        if (_listener != null) {
            XXConnection.getInstance().removeMessageReceiveListenner(_listener);
        }
    }

    /**
     * 初始化服务通道
     **/
    public static void registerService() throws ConnectionRegisterException {
        XXConnection.getInstance().registerService();
    }
}
