package com.xuxian.xximdemo.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 
 * 
 * 版 权 :@Copyright 汉银创新科技有限公司
 * 
 * 作 者 :desperado
 * 
 * 版 本 :4.0
 * 
 * 创建日期 :2015-10-25 下午8:02:08
 * 
 * 描 述 :Json解析和生成工具类
 * 
 * 修订日期 :
 */

public class JsonHelper {
	/**
	 * 将json串解析为普通对象
	 * 
	 * @param jsonString
	 *            json字符串
	 * @param cls
	 *            需要生成对象的字节码
	 * @return
	 */
	public static <T> T parseObjectByJsonStr(String jsonString, Class<T> cls) {
		T t = null;
		try {
			t = JSON.parseObject(jsonString, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将json串解析为一个list集合
	 * 
	 * @param jsonString
	 *            json字符串
	 * @param cls
	 *            需要生成集合的实例对象的字节码文件
	 * @return
	 */
	public static <T> List<T> parseListByJsonStr(String jsonString, Class<T> cls) {
		List<T> list = null;
		try {
			list = JSON.parseArray(jsonString, cls);
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * 将json串解析为一个list<Map<String,Object>>类型的对象；
	 * 
	 * @param jsonString
	 *            需要转换的json字符串
	 * @return
	 */
	public static List<Map<String, Object>> parseListKeyMapByJsonStr(
			String jsonString) {
		List<Map<String, Object>> listMap = null;
		try {
			listMap = JSON.parseObject(jsonString,
					new TypeReference<List<Map<String, Object>>>() {
					});
		} catch (Exception e) {
			return null;
		}
		return listMap;
	}

	/**
	 * 将一个json字符串解析为一个map集合
	 * 
	 * @param jsonStr
	 *            需要转换json字符串
	 * @return
	 */
	public static Map<String, Object> parseMapByJsonStr(String jsonStr) {
		Map<String, Object> map = null;
		try {
			map = JSON.parseObject(jsonStr, HashMap.class);
		} catch (Exception e) {
			return null;
		}
		return map;
	}

	/**根据一个实例对象生成对应的json字符串
	 * @param obj  实例对象
	 * @return
	 */
	public static String generateJsonStrByObject(Object obj) {
		String jsonString = null;
		try {
			jsonString = JSON.toJSONString(obj, true);
		} catch (Exception e) {
			return null;
		}
		return jsonString;
	}
}
