package com.xuxian.xximdemo.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Hashtable;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/8/10. 17:25
 * 版本：
 */
public class MessageBean {

    /**
     * from :
     * fromAvatar : 1
     * fromNick :
     * time :
     * to :
     * type :
     * unread : false
     */

    private String from;
    private int fromAvatar = 1;// 头像
    private String fromNick;
    private String time;
    private String to;
    private String type;
    private boolean unread;
    private XXMessageBody mXXMessageBody; //消息实体
    private Hashtable<String, Object> attributes;//扩展属性

    public MessageBean(){

    }

    public MessageBean(String from, String to, String type, String content, String time, String fromNick) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.time = time;
        this.fromNick = fromNick;
    }

    public XXMessageBody getXXMessageBody() {
        return mXXMessageBody;
    }

    public void setXXMessageBody(XXMessageBody _XXMessageBody) {
        mXXMessageBody = _XXMessageBody;
    }


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(int fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getFromNick() {
        return fromNick;
    }

    public void setFromNick(String fromNick) {
        this.fromNick = fromNick;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUnread() {
        return unread;
    }

    public void setUnread(boolean unread) {
        this.unread = unread;
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
