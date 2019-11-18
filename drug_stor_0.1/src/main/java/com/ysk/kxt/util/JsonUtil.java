package com.ysk.kxt.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * <pre>
 * json打解包
 * </pre>
 */
public class JsonUtil {
	
	/**
	 * JSON字符串打包成JavaBean
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @param clazz
	 *            JavaBean的类描述Class
	 * @return 返回Object,调用方可以强制转换 如: Student s =
	 *         (Student)unpackToBean({"stuName":"测试","stuAge":20}, Student.class);
	 */
	public static Object unPackToBean(String jsonStr, Class<?> clazz) {
		
		Gson gson = new GsonBuilder().create();
		Object unPackBean = gson.fromJson(jsonStr, clazz);
		return unPackBean;
	}
	
	/**
	 * JSON字符串打包成Map<String, Object>
	 * 
	 * @param jsonStr
	 *            JSON字符串
	 * @return 返回Map<String, Object>类
	 */
	public static Map<String, Object> unPackToMap(String jsonStr) {
		
		Gson gson = new GsonBuilder().create();
		Map<String, Object> unPackMap = gson.fromJson(jsonStr, new TypeToken<Map<String, Object>>() {
		}.getType());
		return unPackMap;
	}
	
	/**
	 * Object对象转成JSON字符串
	 * 
	 * @param jsonObj
	 * @return 返回符合格式的JSON字符串
	 */
	public static String packByBean(Object jsonObj) {
		
		Gson gson = new GsonBuilder().create();
		String transJson = gson.toJson(jsonObj);
		return transJson;
	}
	
	/**
	 * 全局返回码JSON格式包装类
	 * 
	 * @param errCode
	 *            返回码
	 * @param errMsg
	 *            返回说明
	 * @return {"errCode":"10001","errMsg":"参数为空"}
	 */
	public static String packByGlobal(String errCode, String errMsg) {
		StringBuilder sb = new StringBuilder();
		sb.append("{").append("\"errCode\":\"").append(errCode).append("\",");
		sb.append("\"errMsg\":\"").append(errMsg).append("\"}");
		return sb.toString();
	}
	
	public static void main(String[] args) {
		String s = JsonUtil.packByGlobal("10001", "参数为空");
		System.out.println(s);
		
	}
}
