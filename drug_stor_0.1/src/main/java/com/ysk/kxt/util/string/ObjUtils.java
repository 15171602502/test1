package com.ysk.kxt.util.string;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author 
 *
 */
public class ObjUtils {
	
	/**
	 * 如果参数object不为空则返回object，如果为空则返回第二个参数defaultValue
	 * 
	 * @param object
	 * @param defaultValue
	 * @return
	 */
	public static Object defaultIfNull(Object object, Object defaultValue) {
		return object != null ? object : defaultValue;
	}
	
	/**
	 * 如果第一个参数object不为空，返回object.toString.trim();如果为空返回defaultValue
	 * 
	 * @param object
	 * @param defaultValue
	 * @return
	 */
	public static String defaultIfNull(Object object, String defaultValue) {
		return object != null ? trimToString(object) : defaultValue;
	}
	
	/**
	 * 
	 * 功能描述：判断参数是否为空，为空时返回NULL（用于数值、日期等类型数据的保存操作）
	 * 
	 * @param obj
	 * @return
	 */
	public static Object defaultNull(Object obj) {
		return "".equals(trimToString(obj)) ? null : obj.toString();
	}
	
	/**
	 * 将Object对象转成String,并去掉空格
	 * 
	 * 
	 * @param obj
	 * @return
	 */
	public static String trimToString(Object obj) {
		return toString(obj).trim();
	}
	
	public static String toString(Object obj) {
		return obj == null ? "" : obj.toString();
	}
	
	/**
	 * 
	 * 功能描述：截取时间的日期部分
	 * 
	 * @param obj
	 * @return
	 */
	public static String timeToYYYYMMDD(Object obj) {
		if (obj != null && !"".equals(obj)) {
			return obj.toString().substring(0, 10);
		}
		return "";
	}
	
	/**
	 * 找出bList中不包含在aList中的数据
	 * 
	 * @param aList
	 * @param bList
	 * @return
	 */
	public static List<String> containsList(List<String> aList, List<String> bList) {
		Map map = new HashMap<String, String>();
		List<String> list = new LinkedList<String>();
		list.addAll(aList);
		String tmp = null;
		for (String string : aList) {
			map.put(string, string);
		}
		for (String string : bList) {
			string = StrUtils.trim(string);
			tmp = (String) map.get(string);
			if (string.equals(tmp)) {
				list.remove(tmp);
			}
		}
		return list;
	}
	
	/**
	 * 
	 * 功能描述：放回int型
	 * 
	 * @param obj
	 * @return
	 */
	public static int toInt(Object obj) {
		if (obj != null && !"".equals(obj)) {
			return Integer.parseInt(obj.toString());
		}
		return 0;
	}
	
	public static double todouble(Object obj) {
		if (obj != null && !"".equals(obj)) {
			return Double.parseDouble(obj.toString());
		}
		return 0;
	}
	
	/**
	 * 判断两个Object是否相等
	 * 
	 * @param object1
	 * @param object2
	 * @return
	 */
	public static boolean equals(Object object1, Object object2) {
		if (object1 == object2) {
			return true;
		}
		if ((object1 == null) || (object2 == null)) {
			return false;
		}
		return object1.equals(object2);
	}
	
	public static String identityToString(Object object) {
		if (object == null) {
			return null;
		}
		StringBuffer buffer = new StringBuffer();
		identityToString(buffer, object);
		return buffer.toString();
	}
	
	public static void identityToString(StringBuffer buffer, Object object) {
		if (object == null) {
			throw new NullPointerException("Cannot get the toString of a null identity");
		}
		buffer.append(object.getClass().getName()).append('@')
				.append(Integer.toHexString(System.identityHashCode(object)));
	}
	
	/**
	 * 功能描述：格式化要更新的列名，如NAME=:NAME
	 * 
	 * @param m
	 *            列名数据集
	 * 
	 * @return
	 */
	public static String columns(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		Object[] cols = (Object[]) map.keySet().toArray();
		for (int i = 0; i < cols.length; i++) {
			sb.append(cols[i] + "=:" + cols[i] + ",");
		}
		if (sb.lastIndexOf(",") != -1) {
			sb.delete(sb.length() - 1, sb.length());
		}
		return sb.toString();
	}
}
