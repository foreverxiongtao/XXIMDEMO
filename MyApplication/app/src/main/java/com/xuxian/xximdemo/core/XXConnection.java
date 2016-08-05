package com.xuxian.xximdemo.core;

import com.xuxian.xximdemo.bean.XXMessage;
import com.xuxian.xximdemo.global.LocalConstant;
import com.xuxian.xximdemo.listener.MessageReceiveListener;
import com.xuxian.xximdemo.listener.RemoteServerStatusListenner;
import com.xuxian.xximdemo.utils.ThreadManager;

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
public class XXConnection {

    private WebSocketClient mClient; //消息连接通道
    private List<MessageReceiveListener> mMessageReceiveListeners = new ArrayList<>();
    private List<RemoteServerStatusListenner> mRemoteServerStatusListeners = new ArrayList<>();
    private static volatile XXConnection mInstance;

    private XXConnection() {

    }

    /***
     * 获取实例对象
     *
     * @return
     */
    public static XXConnection getInstance() {
        if (mInstance == null) {
            synchronized (XXConnection.class) {
                if (mInstance == null) {
                    mInstance = new XXConnection();
                }
            }
        }
        return mInstance;
    }


    /***
     * 添加到消息监听
     *
     * @param _listener
     */
    public void addMessageReceiveListener(MessageReceiveListener _listener) {
        if (_listener != null && mMessageReceiveListeners != null)
            mMessageReceiveListeners.add(_listener);
    }

    /***
     * 移除消息监听
     *
     * @param _listener
     */
    public void removeMessageReceiveListenner(MessageReceiveListener _listener) {
        if (_listener != null && mMessageReceiveListeners != null) {
            mMessageReceiveListeners.remove(_listener);
        }
    }

    /***
     * 添加消息通道状态监听
     *
     * @param _listener
     */
    public void addRemoteServerStatusListener(RemoteServerStatusListenner _listener) {
        if (_listener != null && mRemoteServerStatusListeners != null)
            mRemoteServerStatusListeners.add(_listener);
    }

    /***
     * 移除消息通道状态监听
     *
     * @param _listener
     */
    public void removeRemoteServerStatusListener(RemoteServerStatusListenner _listener) {
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
                public void onOpen(final ServerHandshake handshakedata) {
                    ThreadManager.newInstance().executeShortTack(new Runnable() {
                        @Override
                        public void run() {
                            for (RemoteServerStatusListenner listener : mRemoteServerStatusListeners) {
                                if (listener != null) {
                                    listener.on0pen(handshakedata);
                                }
                            }
                        }
                    });
                }

                @Override
                public void onMessage(final String message) {
                    ThreadManager.newInstance().executeShortTack(new Runnable() {
                        @Override
                        public void run() {
                            for (MessageReceiveListener listener : mMessageReceiveListeners) {
                                if (listener != null) {
                                    listener.onMessageReceive(message);
                                }
                            }
                        }
                    });
                }

                @Override
                public void onClose(final int code, final String reason, final boolean remote) {
                    ThreadManager.newInstance().executeShortTack(new Runnable() {
                        @Override
                        public void run() {
                            for (RemoteServerStatusListenner listener : mRemoteServerStatusListeners) {
                                if (listener != null) {
                                    listener.onClose(code, reason, remote);
                                }
                            }
                        }
                    });
                }

                @Override
                public void onError(final Exception ex) {
                    ThreadManager.newInstance().executeShortTack(new Runnable() {
                        @Override
                        public void run() {
                            for (RemoteServerStatusListenner listener : mRemoteServerStatusListeners) {
                                if (listener != null) {
                                    listener.onError(ex);
                                }
                            }
                        }
                    });
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
        mClient.connect();
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
    public void sendMessage(final XXMessage _message) {
        if (mClient != null) {
            ThreadManager.newInstance().executeShortTack(new Runnable() {
                @Override
                public void run() {
                    mClient.send(_message.toJson());
                }
            });
        }
    }

}
