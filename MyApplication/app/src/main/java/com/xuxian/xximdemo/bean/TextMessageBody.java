package com.xuxian.xximdemo.bean;

import android.os.Parcel;

/*
 *
 *
 * 版 权 :@Copyright 北京优多鲜道科技有限公司版权所有
 *
 * 作 者 :desperado
 *
 * 版 本 :1.0
 *
 * 创建日期 :2016/8/9  18:13
 *
 * 描 述 :文本消息体
 *
 * 修订日期 :
 */
public class TextMessageBody extends XXMessageBody {
    private String content;

    private TextMessageBody(Parcel in) {
        this.content = in.readString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String _content) {
        content = _content;
    }

    public TextMessageBody(String _content) {
        this.content = _content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.content);
    }

    public static final Creator<TextMessageBody> CREATOR = new Creator<TextMessageBody>() {
        @Override
        public TextMessageBody createFromParcel(Parcel in) {
            return new TextMessageBody(in);
        }

        @Override
        public TextMessageBody[] newArray(int size) {
            return new TextMessageBody[size];
        }
    };
}
