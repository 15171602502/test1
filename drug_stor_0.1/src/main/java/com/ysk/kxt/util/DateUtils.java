package com.ysk.kxt.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class DateUtils {
	
	/**
	 * 判断addtime 是否在含今天的7天内
	 */
	
	public static boolean isinsideWeek(Date addtime, Date now) {
		Calendar calendar = Calendar.getInstance(); // 得到日历
		calendar.setTime(now);// 把当前时间赋给日历
		calendar.add(Calendar.DAY_OF_MONTH, 7); // 设置为7天后
		Date after7days = calendar.getTime(); // 得到7天后的时间
		if (after7days.getTime() > addtime.getTime() && addtime.getTime() > now.getTime()) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断2个日期的时间差 date1在后 date2在前
	 * 
	 * 
	 * @param date1
	 *            时间1
	 * @param format
	 *            时间1的格式
	 * @param date2
	 *            当前时间
	 * @throws ParseException
	 */
	public static String difftime(String date1, String format, Date date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date1));
		
		long a = calendar.getTimeInMillis();
		
		calendar.setTime(date2);
		long b = calendar.getTimeInMillis();
		long between_days = (a - b) / (1000 * 3600 * 24);
		if (between_days < 0) {
			between_days = 0;
		}
		long between_house = (a - b - (between_days * 1000 * 3600 * 24)) / (1000 * 3600);
		if (between_house < 0) {
			between_house = 0;
		}
		return String.valueOf(between_days + "天" + between_house + "小时");
		
	}
	
	/**
	 * 
	 * 计算倒计时
	 * 
	 * @param date1
	 *            创建时间
	 * @param date2
	 *            倒计时总时间（分）
	 * @throws ParseException
	 */
	public static Map countingDown(String date1, String format, int date2) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(sdf.parse(date1));
		
		// 计算失效时间
		calendar.add(Calendar.MINUTE, date2);
		long b = calendar.getTimeInMillis();
		calendar.setTime(new Date());
		long a = calendar.getTimeInMillis();
		long between_days = b - a;
		Map<String, Object> map = new HashMap<>();
		// 相差分数
		long min = between_days / (1000 * 60);
		System.out.println();
		long second = (between_days - min * (1000 * 60)) / (1000);
		if (min < 0) {
			min = 0;
		}
		if (second < 0) {
			second = 0;
		}
		map.put("min", min);
		map.put("second", second);
		
		return map;
	}
	
	public static void main(String[] args) throws ParseException {
		
		System.out.println(checkAMPM("am"));
	}
	
	/**
	 * @Description 判断输入的
	 * @date 2017年11月13日
	 * @author 邓金
	 * @param sourceDate
	 */
	public static boolean checkAMPM(String sourceDate) {
		
		GregorianCalendar cal = new GregorianCalendar();
		if (cal.get(GregorianCalendar.AM_PM) == 0) {
			//说明是上午
			return true;
			
		} else if (cal.get(GregorianCalendar.AM_PM) == 1) {
			//说明是下午
			if ("am".equals(sourceDate)) {
				return false;
			}
		}
		return true;
	}
	
}
