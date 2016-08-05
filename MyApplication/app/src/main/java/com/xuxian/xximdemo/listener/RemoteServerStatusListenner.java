package com.xuxian.xximdemo.listener;

import org.java_websocket.handshake.ServerHandshake;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/5  15:16
 *
 * 描 述 :消息通道状态监听
 *
 * 修订日期 :
 */
public interface RemoteServerStatusListenner {
    /**
     * 开启通道回调
     *
     * @param handshakedata
     */
    void on0pen(ServerHandshake handshakedata);

    /**
     * 断开通道回调
     *
     * @param code   状态码
     * @param reason 原因
     * @param remote 是否是远程的
     */
    void onClose(int code, String reason, boolean remote);

    /***
     * 错误回调
     *
     * @param ex
     */
    void onError(Exception ex);
}
