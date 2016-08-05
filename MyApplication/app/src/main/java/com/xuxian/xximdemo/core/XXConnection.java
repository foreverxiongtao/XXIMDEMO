package com.xuxian.xximdemo.core;

import com.xuxian.xximdemo.bean.XXMessage;
import com.xuxian.xximdemo.global.LocalConstant;
import com.xuxian.xximdemo.listener.MessageReceiveListener;
import com.xuxian.xximdemo.listener.RemoteServerStatusListenner;

import org.java_websocket.WebSocketImpl;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/5  14:16
 *
 * 描 述 :消息连接对象
 *
 * 修订日期 :
 */
public class XXConnection extends Thread {

    private WebSocketClient mClient; //消息连接通道
    private List<MessageReceiveListener> mMessageReceiveListeners = new ArrayList<>();
    private List<RemoteServerStatusListenner> mRemoteServerStatusListeners = new ArrayList<>();


    /***
     * 添加到消息监听
     *
     * @param _listener
     */
    private void addMessageReceiveListener(MessageReceiveListener _listener) {
        if (_listener != null && mMessageReceiveListeners != null)
            mMessageReceiveListeners.add(_listener);
    }

    /***
     * 移除消息监听
     *
     * @param _listener
     */
    private void removeMessageReceiveListenner(MessageReceiveListener _listener) {
        if (_listener != null && mMessageReceiveListeners != null) {
            mMessageReceiveListeners.remove(_listener);
        }
    }

    /***
     * 添加消息通道状态监听
     *
     * @param _listener
     */
    private void addRemoteServerStatusListener(RemoteServerStatusListenner _listener) {
        if (_listener != null && mRemoteServerStatusListeners != null)
            mRemoteServerStatusListeners.add(_listener);
    }

    /***
     * 移除消息通道状态监听
     *
     * @param _listener
     */
    private void removeRemoteServerStatusListener(RemoteServerStatusListenner _listener) {
        if (_listener != null && mRemoteServerStatusListeners != null) {
            mRemoteServerStatusListeners.remove(_listener);
        }
    }

    /**
     * 初始化连接通道
     **/
    private void init() {
        try {
            mClient = new WebSocketClient(new URI(LocalConstant.REMOTE_ADDRESS + ":" + LocalConstant.REMOTE_PORT), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                    for (RemoteServerStatusListenner listener : mRemoteServerStatusListeners) {
                        if (listener != null) {
                            listener.on0pen(handshakedata);
                        }
                    }
                }

                @Override
                public void onMessage(String message) {
                    for (MessageReceiveListener listener : mMessageReceiveListeners) {
                        if (listener != null) {
                            listener.onMessageReceive(message);
                        }
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    for (RemoteServerStatusListenner listener : mRemoteServerStatusListeners) {
                        if (listener != null) {
                            listener.onClose(code, reason, remote);
                        }
                    }
                }

                @Override
                public void onError(Exception ex) {
                    for (RemoteServerStatusListenner listener : mRemoteServerStatusListeners) {
                        if (listener != null) {
                            listener.onError(ex);
                        }
                    }
                }
            };
            WebSocketImpl.DEBUG = true;
            System.setProperty("java.net.preferIPv6Addresses", "false");
            System.setProperty("java.net.preferIPv4Stack", "true");
        } catch (URISyntaxException _e) {
            _e.printStackTrace();
        }
    }

    /***
     * 连接夫妇器
     */
    public void open() {
        if (mClient == null) {
            init();
        }
    }

    /****
     * 断开连接
     */
    public void close() {
        if (mClient != null) {
            mClient.close();
            mClient = null;
        }
    }

    /***
     * 发送消息
     *
     * @param _message
     */
    public void sendMessage(XXMessage _message) {
        if (mClient != null) {
            mClient.send(_message.toJson());
        }
    }
}
