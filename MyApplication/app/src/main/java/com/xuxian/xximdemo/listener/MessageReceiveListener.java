package com.xuxian.xximdemo.listener;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/5  14:30
 *
 * 描 述 :消息监听器
 *
 * 修订日期 :
 */
public interface MessageReceiveListener {
    /**
     * 接收消息
     **/
    void onMessageReceive(String msg);
}
