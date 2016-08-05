package com.xuxian.xximdemo.bean;

import com.alibaba.fastjson.JSON;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/5  14:09
 *
 * 描 述 :消息体
 *
 * 修订日期 :
 */
public class XXMessage {
    private String from;        //发送者
    private String to;          //接收者
    private String type;        //消息类型
    private String content;    //内容
    private String time;      //发送时间
    private String fromNick = "";// 昵称
//    private int fromAvatar = 1;// 头像


    public XXMessage(String from, String to, String type, String content, String time, String fromNick) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.content = content;
        this.time = time;
        this.fromNick = fromNick;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String _from) {
        from = _from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String _to) {
        to = _to;
    }

    public String getType() {
        return type;
    }

    public void setType(String _type) {
        type = _type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String _content) {
        content = _content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String _time) {
        time = _time;
    }

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String _fromNick) {
        fromNick = _fromNick;
    }

    public String toJson() {
        return JSON.toJSONString(this);
    }
}
