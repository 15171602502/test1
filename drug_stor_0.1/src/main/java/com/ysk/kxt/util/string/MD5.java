package com.ysk.kxt.util.string;

import java.security.MessageDigest;

/**
 * 
 * 密码加密
 * 
 * @author yanglei
 * @version [MedHospital0001.1.0, 2011-5-16]
 * @see [相关类/方法]
 * @since [MedHospital0001/1.0]
 */
public class MD5 {
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };
	
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}
	
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * 对字符串进行MD5编码
	 * 
	 * @param origin
	 * @return
	 */
	public static String encode(String origin) {
		String resultString = null;
		
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return resultString;
	}
	
	// 加密后解密   
	public static String JM(String inStr) {
		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++) {
			a[i] = (char) (a[i] ^ 't');
		}
		String k = new String(a);
		return k;
	}
	
}