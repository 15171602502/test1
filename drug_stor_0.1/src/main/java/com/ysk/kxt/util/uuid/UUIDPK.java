/*
 * Created on 2005-2-17
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ysk.kxt.util.uuid;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ysk.kxt.util.string.StrUtils;

/**
 * <p>
 * Title:UUID主键生成器
 * 
 * </p>
 * <p>
 * Description:根据UUID规则，生成主键
 * 
 * </p>
 * <p>
 * Date:2006-02-16
 * </p>
 * <p>
 * Copyright:Synjones Copyright (c) 2006
 * </p>
 * 
 * @version 1.0
 */
public class UUIDPK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1573574136923745902L;

	public String POID;

	/**
	 * 这个无参的构造函数是必须要有的。
	 * 
	 */
	public UUIDPK() {
	}

	/**
	 * 
	 * @function: @param id POID的值。
	 * 
	 * @return @throws
	 */
	public UUIDPK(String id) {
		POID = id;
	}

	@Override
	public String toString() {
		return POID;
	}

	@Override
	public boolean equals(Object other) {
		boolean result = false;
		if (other == this) {
			return true;
		}
		if (!(other instanceof UUIDPK)) {
			return false;
		}

		result = POID == null ? ((UUIDPK) other).POID == null : POID.equals(((UUIDPK) other).POID);

		return result;
	}

	@Override
	public int hashCode() {
		return POID == null ? 0 : POID.hashCode();
	}

	// --------以下为UUID的生成函数---------
	/**
	 * 
	 * @function: 按UUID的产生机制产生一个32位长的Hex字符串。
	 * 
	 * @param obj
	 *            生成UUID的thirdPart时所需要使用的对象的实例引用。
	 * 
	 * @return @throws
	 */

	public static String getUUID(Object obj) {
		String uuid = "";
		uuid = firstPart() + secondPart() + thirdPart(obj) + fourthPart();
		return uuid;
	}

	/**
	 * 
	 * @function: 按UUID的产生机制产生一个21位长的Hex字符串。
	 * 
	 * @param obj
	 *            生成UUID的thirdPart时所需要使用的对象的实例引用。
	 * 
	 * @return @throws
	 */

	public static String getUUID21(Object obj) {
		String uuid = "";
		uuid = firstPart() + secondPart() + thirdPart(obj);
		return uuid.substring(0, 20);
	}

	/**
	 * 
	 * 功能描述：按UUID的产生机制产生一个至少5位长的Hex字符串。
	 * 
	 * 
	 * @param obj
	 * @param num
	 * @return
	 */
	public static String getUUIDs(Object obj, int num) {
		String uuid = "";
		uuid = firstPart() + secondPart() + thirdPart(obj);
		return uuid.substring(0, num);
	}

	/**
	 * 
	 * @function: 返回当前时间（毫秒）表示的long型数字（Hex格式）的低8位。
	 * 
	 * @param @return 返回当前时间（毫秒）表示的long型数字（Hex格式）的低8位
	 * 
	 * @throws
	 */
	private static String firstPart() {
		long tmp = 0L;
		String tmpHex = null;
		tmp = System.currentTimeMillis();
		tmpHex = Long.toHexString(tmp);
		return tmpHex.substring(tmpHex.length() - 8);
	}

	/**
	 * 
	 * @function: 底层IP地址表示的32位整数的Hex字符串。
	 * 
	 * @param @return 底层IP地址表示的32位整数的Hex字符串。
	 * 
	 * @throws
	 */
	private static String secondPart() {
		String sndPart = "";
		int tmpIp = 0;
		int tmp = 0;
		InetAddress localIPAddr = null;
		try {
			localIPAddr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			// 基本不可能发生。

		}
		byte[] ipParts = localIPAddr.getAddress();
		// 0
		tmp = ipParts[0];
		/** IP地址的第0段，tmp左移24位 */
		tmpIp = tmp << 24;
		// 1
		tmp = ipParts[1];
		tmp = tmp << 16;
		tmpIp = tmpIp ^ tmp;
		// 2
		tmp = ipParts[2];
		tmp = tmp << 8;
		tmpIp = tmpIp ^ tmp;
		// 3
		tmp = ipParts[3];
		tmpIp = tmpIp ^ tmp;
		sndPart = Integer.toHexString(tmpIp);
		return getEightHex(sndPart);
	}

	/**
	 * 
	 * @function: 调用对象的HashCode码的Hex形式的字符串。
	 * 
	 * @param obj 使用该类生成hashCode。
	 * 
	 * @return 调用对象的HashCode码的Hex形式的字符串。
	 * 
	 * @throws
	 */
	private static String thirdPart(Object obj) {
		String tmpHex = "";
		tmpHex = Integer.toHexString(obj.hashCode());
		return getEightHex(tmpHex);
	}

	/**
	 * 
	 * @function: SecureRandom类所产生的在一毫秒内对同一个方法的多个调用的唯一值。
	 * 
	 * @param @return 在一毫秒内对同一个方法的多个调用的唯一值。
	 * 
	 * @throws
	 */
	private static String fourthPart() {
		String tmpHex = "";
		SecureRandom sr = new SecureRandom();
		tmpHex = Integer.toHexString(sr.nextInt());
		return getEightHex(tmpHex);
	}

	/**
	 * 生成随机数
	 * 
	 * 
	 * @return
	 */
	public static String getId() {
		String temp = String.valueOf(Math.random());
		temp = temp.replace("0.", "");
		return temp;
	}

	/**
	 * 
	 * @function: 把传递过来的字符串加工成8位长。
	 * 
	 * @param part 要处理的字符串。
	 * 
	 * @return 如果字符串不足8位，返回8位长的字符串（前几位补0）；否则返回原字符串。
	 * 
	 * @throws
	 */
	private static String getEightHex(String part) {
		if (part.length() >= 8) {
			return part;
		}
		switch (part.length()) {
		case (0): {
			part = "00000000";
			break;
		}
		case (1): {
			part = "0000000" + part;
			break;
		}
		case (2): {
			part = "000000" + part;
			break;
		}
		case (3): {
			part = "00000" + part;
			break;
		}
		case (4): {
			part = "0000" + part;
			break;
		}
		case (5): {
			part = "000" + part;
			break;
		}
		case (6): {
			part = "00" + part;
			break;
		}
		case (7): {
			part = "0" + part;
			break;
		}
		}
		return part;
	}

	/**
	 * 获取预约验证码
	 * 
	 * @return
	 */
	public static String getReservePwd() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("MMddHHmm");
		// long tmp = 0L;
		// tmp = System.currentTimeMillis();
		String first = sdf.format(cal.getTime());
		String rd = String.valueOf(Math.random());
		String second = rd.replace("0.", "").substring(0, 5);
		return first + second;
	}

	/**
	 * 
	 * 生成16位时间戳随机数 月日 加4位随机数
	 */
	public static String get8BitsId() {
		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();

		if (cal.get(Calendar.MONTH) + 1 > 10) {
			sb.append(cal.get(Calendar.MONTH) + 1);
		} else {

			sb.append("0" + (cal.get(Calendar.MONTH) + 1));
		}

		if (cal.get(Calendar.DAY_OF_MONTH) > 10) {

			sb.append(cal.get(Calendar.DAY_OF_MONTH));
		} else {
			sb.append("0" + cal.get(Calendar.DAY_OF_MONTH));
		}
		int n = 0;
		while (n < 1000) {
			n = (int) (Math.random() * 10000);
		}
		sb.append(n);

		return sb.toString();
	}

	/**
	 * 
	 * 生成16位时间戳随机数 年月日时分秒 加4位随机数
	 */
	public static String get16BitsId() {

		Calendar cal = Calendar.getInstance();
		StringBuffer sb = new StringBuffer();

		// 获取年月日 并补齐二位数
		Integer year = cal.get(Calendar.YEAR);

		sb.append(String.valueOf(year).substring(2, 4));
		if (cal.get(Calendar.MONTH) + 1 > 9) {
			sb.append(cal.get(Calendar.MONTH) + 1);
		} else {

			sb.append("0" + (cal.get(Calendar.MONTH) + 1));
		}

		if (cal.get(Calendar.DAY_OF_MONTH) > 9) {

			sb.append(cal.get(Calendar.DAY_OF_MONTH));
		} else {
			sb.append("0" + cal.get(Calendar.DAY_OF_MONTH));
		}

		if (cal.get(Calendar.HOUR_OF_DAY) > 9) {
			sb.append(cal.get(Calendar.HOUR_OF_DAY));
		} else {

			sb.append("0" + cal.get(Calendar.HOUR_OF_DAY));
		}

		if (cal.get(Calendar.MINUTE) > 9) {
			sb.append(cal.get(Calendar.MINUTE));
		} else {
			sb.append("0" + cal.get(Calendar.MINUTE));
		}

		if (cal.get(Calendar.SECOND) > 9) {
			sb.append(cal.get(Calendar.SECOND));
		} else {
			sb.append("0" + cal.get(Calendar.SECOND));
		}

		int n = 0;
		while (n < 1000) {
			n = (int) (Math.random() * 10000);
		}
		sb.append(n);

		return sb.toString();

	}
}
