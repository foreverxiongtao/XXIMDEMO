package com.xuxian.xximdemo.util;

import com.thoughtworks.xstream.XStream;

/**
 * 类名称：
 * 类描述：
 * 创建人：quzongyang
 * 创建时间：2016/8/5. 15:04
 * 版本：
 */
public class FormatXML {
    public String toXml() {
        // 创建Xstream
        XStream x = new XStream();
        x.alias(getClass().getSimpleName(), getClass());
        String xml = x.toXML(this);
        return xml;
    }

    public Object fromXml(String xml) {
        // 创建Xstream
        XStream x = new XStream();
        x.alias(getClass().getSimpleName(), getClass());
        return x.fromXML(xml);
    }


}
