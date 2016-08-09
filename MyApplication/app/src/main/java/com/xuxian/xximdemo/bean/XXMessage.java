package com.xuxian.xximdemo.bean;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

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
    private String time;      //发送时间
    private String fromNick = "";// 昵称
    private int fromAvatar = 1;// 头像
    private Hashtable<String, Object> attributes;//扩展属性
    private boolean unread;//是否已经阅读

    private XXMessageBody mXXMessageBody; //消息实体

    public int getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(int _fromAvatar) {
        fromAvatar = _fromAvatar;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean _unread) {
        unread = _unread;
    }

    public XXMessageBody getXXMessageBody() {
        return mXXMessageBody;
    }

    public void setXXMessageBody(XXMessageBody _XXMessageBody) {
        mXXMessageBody = _XXMessageBody;
    }

    public XXMessage(String from, String to, String type, String content, String time, String fromNick) {
        this.from = from;
        this.to = to;
        this.type = type;
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

    public void setAttribute(String key, boolean var2) {
        if (this.attributes == null) {
            this.attributes = new Hashtable();
        }

        this.attributes.put(key, new Boolean(var2));
    }

    public void setAttribute(String var1, int var2) {
        if (this.attributes == null) {
            this.attributes = new Hashtable();
        }

        this.attributes.put(var1, new Integer(var2));
    }

    public void setAttribute(String var1, JSONObject var2) {
        if (this.attributes == null) {
            this.attributes = new Hashtable();
        }

        this.attributes.put(var1, var2);
    }

    public void setAttribute(String var1, JSONArray var2) {
        if (this.attributes == null) {
            this.attributes = new Hashtable();
        }

        this.attributes.put(var1, var2);
    }

    public void setAttribute(String var1, String var2) {
        if (this.attributes == null) {
            this.attributes = new Hashtable();
        }
        this.attributes.put(var1, var2);
    }

    public boolean getBooleanAttribute(String var1) throws Exception {
        Boolean var2 = null;
        if (this.attributes != null) {
            Object var3 = this.attributes.get(var1);
            if (var3 instanceof Boolean) {
                var2 = (Boolean) var3;
            } else if (var3 instanceof Integer) {
                int var4 = ((Integer) var3).intValue();
                if (var4 > 0) {
                    var2 = Boolean.valueOf(true);
                } else {
                    var2 = Boolean.valueOf(false);
                }
            }
        }

        if (var2 == null) {
            throw new Exception("attribute " + var1 + " not found");
        } else {
            return var2.booleanValue();
        }
    }

    public boolean getBooleanAttribute(String var1, boolean var2) {
        if (this.attributes == null) {
            return var2;
        } else {
            Boolean var3 = null;

            try {
                var3 = Boolean.valueOf(this.getBooleanAttribute(var1));
            } catch (Exception var5) {
                ;
            }

            return var3 == null ? var2 : var3.booleanValue();
        }
    }

    public int getIntAttribute(String var1, int var2) {
        Integer var3 = null;
        if (this.attributes != null) {
            var3 = (Integer) this.attributes.get(var1);
        }

        return var3 == null ? var2 : var3.intValue();
    }

    public int getIntAttribute(String var1) throws Exception {
        Integer var2 = null;
        if (this.attributes != null) {
            var2 = (Integer) this.attributes.get(var1);
        }

        if (var2 == null) {
            throw new Exception("attribute " + var1 + " not found");
        } else {
            return var2.intValue();
        }
    }

    public String getStringAttribute(String var1) throws Exception {
        if (this.attributes != null && this.attributes.containsKey(var1)) {
            Object var2 = this.attributes.get(var1);
            if (var2 instanceof String) {
                return (String) var2;
            } else if (var2 instanceof JSONObject) {
                return ((JSONObject) var2).toString();
            } else if (var2 instanceof JSONArray) {
                return ((JSONArray) var2).toString();
            } else {
                throw new Exception("attribute " + var1 + " not String type");
            }
        } else {
            throw new Exception("attribute " + var1 + " not found");
        }
    }

    public String getStringAttribute(String var1, String var2) {
        if (this.attributes != null && this.attributes.containsKey(var1)) {
            Object var3 = this.attributes.get(var1);
            if (var3 instanceof String) {
                return (String) var3;
            }

            if (var3 instanceof JSONObject) {
                return ((JSONObject) var3).toString();
            }

            if (var3 instanceof JSONArray) {
                return ((JSONArray) var3).toString();
            }
        }

        return var2;
    }

    public JSONObject getJSONObjectAttribute(String var1) throws Exception {
        if (this.attributes != null && this.attributes.containsKey(var1)) {
            Object var2 = this.attributes.get(var1);
            if (var2 instanceof JSONObject) {
                return (JSONObject) var2;
            } else {
                if (var2 instanceof String) {
                    try {
                        JSONObject var3 = new JSONObject((String) var2);
                        return var3;
                    } catch (JSONException var4) {
                        ;
                    }
                }

                throw new Exception("attribute " + var1 + " not JSONObject type");
            }
        } else {
            throw new Exception("attribute " + var1 + " not found");
        }
    }

    public JSONArray getJSONArrayAttribute(String var1) throws Exception {
        if (this.attributes != null && this.attributes.containsKey(var1)) {
            Object var2 = this.attributes.get(var1);
            if (var2 instanceof JSONArray) {
                return (JSONArray) var2;
            } else {
                if (var2 instanceof String) {
                    try {
                        JSONArray var3 = new JSONArray((String) var2);
                        return var3;
                    } catch (JSONException var4) {
                        ;
                    }
                }

                throw new Exception("attribute " + var1 + " not JSONArray type");
            }
        } else {
            throw new Exception("attribute " + var1 + " not found");
        }
    }

}
