/**
 * License. Copyright ?2007, All right Reserved. This file belongs to Wuhan ShineCode Ltd. Description collect all
 * functions of general String process 字符串工具类, 一些方便的字符串工具方法. Create on : 2007-6-23 Author : Henry.He
 */
package com.ysk.kxt.util.string;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 * 
 * @author 常宵阳
 *
 */
public class StrUtils {
	
	/**
	 * 屏蔽手机号码中间数字.
	 * 
	 * @param Phone
	 *            输入的字串
	 * 
	 */
	
	public static String maskPhone(String phone) {
		StringBuffer sb = new StringBuffer();
		if (phone.length() == 11) {
			sb.append(phone.substring(0, 3));
			sb.append("****");
			sb.append(phone.substring(phone.length() - 4, phone.length()));
		} else if (phone.length() == 3) {
			sb.append(phone.substring(0, 1));
			sb.append("*");
			sb.append(phone.substring(phone.length() - 1, phone.length()));
		} else if (phone.length() == 2) {
			sb.append(phone.substring(0, 1));
			sb.append("*");
		}
		return sb.toString();
	}
	
	/**
	 * 将字符串转换为 int.
	 * 
	 * @param input
	 *            输入的字串
	 * 
	 * @param defautlInt
	 *            :
	 * @return 结果数字
	 */
	public static int parseInt(String input, int defaultInt) {
		try {
			return Integer.parseInt(input);
		} catch (Exception e) {
		}
		return defaultInt;
	}
	
	/**
	 * 将字符串转换为 int.
	 * 
	 * @param input
	 *            输入的字串
	 * 
	 * @param defautlInt
	 *            :
	 * @return 结果数字
	 */
	public static int parseInt(Object input, int defaultInt) {
		try {
			if (null == input) {
				return 0;
			} else {
				return Integer.parseInt(input.toString());
			}
		} catch (Exception e) {
		}
		return defaultInt;
	}
	
	/**
	 * 将字符串转换为 float.
	 * 
	 * @param input
	 *            输入的字串
	 * 
	 * @return 结果数字
	 */
	public static float parseFloat(String input, float defaultFloat) {
		try {
			return Float.parseFloat(input);
		} catch (Exception e) {
		}
		return defaultFloat;
	}
	
	@SuppressWarnings("unused")
	public static float parseFloat(Object input, float defaultFloat) {
		if (null == input) {
			return defaultFloat;
		}
		try {
			return Float.parseFloat(input.toString());
		} catch (Exception ex) {
		}
		return defaultFloat;
	}
	
	@SuppressWarnings("static-access")
	public static String formatCurrency(String input) {
		return new DecimalFormat("####.00").getCurrencyInstance(Locale.CHINA).format(parseFloat(input, 0f));
	}
	
	/**
	 * 将传入的以@为分割符的字符串组装为sql
	 * 
	 * @param parms
	 * @return
	 */
	public static String toSqlByEquTypeFilter(String parms) {
		String[] parm = parms.split("@");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < parm.length; i++) {
			sb.append(" and EquRegCode like '" + parm[i] + "%'");
		}
		return sb.toString();
	}
	
	/**
	 * 清除字符串结尾的空格.
	 * 
	 * @param input
	 *            String 输入的字符串
	 * @return 转换结果
	 */
	public static String trimTailSpaces(String input) {
		if (isEmpty(input)) {
			return "";
		}
		
		String trimedString = input.trim();
		
		if (trimedString.length() == input.length()) {
			return input;
		}
		
		return input.substring(0, input.indexOf(trimedString) + trimedString.length());
	}
	
	/**
	 * 
	 * 功能描述：除去字符串的头尾空格
	 * 
	 * @param input
	 *            ：传入的字符串
	 * 
	 * @return 除去空格后的字符串
	 * 
	 */
	public static String trim(String input) {
		if (isEmpty(input)) {
			return "";
		}
		return input.trim();
	}
	
	/**
	 * 
	 * 功能描述：除去字符串的头尾空格
	 * 
	 * @param input
	 *            ：传入的字符串
	 * 
	 * @return 除去空格后的字符串
	 * 
	 */
	public static String trim(Object input) {
		if (isEmpty(input)) {
			return null;
		}
		return input.toString().trim();
	}
	
	/**
	 * Change the null string value to "", if not null, then return it self, use
	 * this to avoid display a null string to "null".
	 * 
	 * @param input
	 *            the string to clear
	 * @return the result
	 */
	public static String clearNull(String input) {
		return isEmpty(input) ? "" : input;
	}
	
	/**
	 * Return the limited length string of the input string
	 * 
	 * @param input
	 *            String
	 * @param maxLength
	 *            int
	 * @return String processed result
	 */
	public static String limitStringLength(String input, int maxLength) {
		if (isEmpty(input))
			return "";
		
		if (input.length() <= maxLength) {
			return input;
		} else {
			return input.substring(0, maxLength - 3) + "...";
		}
		
	}
	
	/**
	 * 转换字符串"a,b"->"'a','b'"
	 * 
	 * @param string
	 */
	public static String stringToColumn(String string) {
		String tmp = "";
		if (string != null) {
			String[] s = string.split(",");
			for (int i = 0; i < s.length; i++) {
				if (i != s.length - 1) {
					tmp += "'" + s[i] + "',";
				} else {
					tmp += "'" + s[i] + "'";
				}
			}
		}
		return tmp;
	}
	
	/**
	 * 将单个的 ' 换成 ''; SQL 规则:如果单引号中的字符串包含一个嵌入的引号,可以使用两个单引号表示嵌入的单引号.
	 **/
	
	public static String replaceSql(String input) {
		
		if (!isEmpty(input)) {
			return replace(input, "'", "‘").replace("\"", "“");
		} else {
			return "";
		}
	}
	
	public static String replaceSql(Object input) {
		if (null != input) {
			return replace(input.toString(), "'", "\'").replace("\"", "“");
		} else {
			return "";
		}
		
	}
	
	/**
	 * 判断字符串是否全是数字字符.
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 判断结果, true 为全数字, false 为还有非数字字符
	 */
	public static boolean isInteger(String input) {
		if (isEmpty(input)) {
			return false;
		}
		
		for (int i = 0; i < input.length(); i++) {
			char charAt = input.charAt(i);
			
			if (!Character.isDigit(charAt)) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 功能描述：浮点数字判断
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null)
			return false;
		Pattern p1 = Pattern.compile("(-|[0-9])?[0-9]*(\\.?)[0-9]+"); // 非數字
		Matcher m = p1.matcher(str);
		boolean b = m.matches(); // matches() 尝试将整个区域与模式匹配
		return b;
	}
	
	/**
	 * 转换由表单读取的数据的内码(从 ISO8859 转换到 gb2312).
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 * 
	 */
	public static String toChi(String input) {
		try {
			byte[] bytes = input.getBytes("ISO8859-1");
			return new String(bytes, "GBK");
		} catch (Exception ex) {
		}
		return input;
	}
	
	/**
	 * 转换由表单读取的数据的内码到 ISO(从 GBK 转换到ISO8859-1).
	 * 
	 * @param input
	 *            输入的字符串
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 * 
	 */
	public static String toISO(String input) {
		return changeEncoding(input, "GBK", "ISO8859-1");
	}
	
	/**
	 * 转换字符串的内码.
	 * 
	 * @param input
	 *            输入的字符串
	 * @param sourceEncoding
	 *            源字符集名称
	 * @param targetEncoding
	 *            目标字符集名称
	 * 
	 * @return 转换结果, 如果有错误发生, 则返回原来的值
	 * 
	 */
	public static String changeEncoding(String input, String sourceEncoding, String targetEncoding) {
		if (input == null || input.equals("")) {
			return input;
		}
		
		try {
			byte[] bytes = input.getBytes(sourceEncoding);
			return new String(bytes, targetEncoding);
		} catch (Exception ex) {
		}
		return input;
	}
	
	// ------------------------------------ 字符串处理方法
	
	// ----------------------------------------------
	
	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, 并以大小写敏感方式进行查找
	 * 
	 * 
	 * @param source
	 *            需要替换的源字符串
	 * @param oldStr
	 *            需要被替换的老字符串
	 * @param newStr
	 *            替换为的新字符串
	 */
	public static String replace(String source, String oldStr, String newStr) {
		return replace(source, oldStr, newStr, true);
	}
	
	/**
	 * 将字符串 source 中的 oldStr 替换为 newStr, matchCase 为是否设置大小写敏感查找
	 * 
	 * @param source
	 *            需要替换的源字符串
	 * @param oldStr
	 *            需要被替换的老字符串
	 * @param newStr
	 *            替换为的新字符串
	 * @param matchCase
	 *            是否需要按照大小写敏感方式查找
	 */
	public static String replace(String source, String oldStr, String newStr, boolean matchCase) {
		if (source == null) {
			return null;
		}
		// 首先检查旧字符串是否存在, 不存在就不进行替换
		
		if (source.toLowerCase().indexOf(oldStr.toLowerCase()) == -1) {
			return source;
		}
		int findStartPos = 0;
		int a = 0;
		while (a > -1) {
			int b = 0;
			String str1, str2, str3, str4, strA, strB;
			str1 = source;
			str2 = str1.toLowerCase();
			str3 = oldStr;
			str4 = str3.toLowerCase();
			if (matchCase) {
				strA = str1;
				strB = str3;
			} else {
				strA = str2;
				strB = str4;
			}
			a = strA.indexOf(strB, findStartPos);
			if (a > -1) {
				b = oldStr.length();
				findStartPos = a + b;
				StringBuffer bbuf = new StringBuffer(source);
				source = bbuf.replace(a, a + b, newStr) + "";
				// 新的查找开始点位于替换后的字符串的结尾
				findStartPos = findStartPos + newStr.length() - b;
			}
		}
		return source;
	}
	
	/**
	 * 将字符串截断成List
	 * 
	 * @param input
	 *            : 传入的字符串
	 * @param f
	 *            ：
	 * 
	 * @return
	 */
	public static List<String> spliptStringToList(String input, String f) {
		if (isEmpty(input)) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		String[] str = input.split(f);
		for (int i = 0; i < str.length; i++) {
			if (!isEmpty(str[i])) {
				list.add(trim(str[i]));
			}
		}
		return list;
	}
	
	/**
	 * 判断字符串是否未空, 如果为 null 或者长度为0, 均返回 true.
	 */
	public static boolean isEmpty(String input) {
		return (input == null || input.length() == 0);
	}
	
	public static boolean isNotEmpty(String input) {
		return (!isEmpty(input));
	}
	
	public static String getBgsbh(String param) {
		return param.substring(param.indexOf("=") + 1, param.length());
	}
	
	/**
	 * 判断字符串是否未空, 如果为 null 或者长度为0, 均返回 true.
	 */
	public static boolean isEmpty(Object input) {
		try {
			if (input == null || "".equals(input.toString().trim()))
				return true;
		} catch (NullPointerException e) {
			return false;
		}
		return false;
	}
	
	/**
	 * 获得输入字符串的字节长度(即二进制字节数), 用于发送短信时判断是否超出长度.
	 * 
	 * @param input
	 *            输入字符串
	 * 
	 * @return 字符串的字节长度(不是 Unicode 长度)
	 */
	public static int getBytesLength(String input) {
		if (input == null) {
			return 0;
		}
		
		int bytesLength = input.getBytes().length;
		return bytesLength;
	}
	
	/**
	 * 检验字符串是否未空, 如果是, 则返回给定的出错信息.
	 * 
	 * @param input
	 *            输入的字符串
	 * @param errorMsg
	 *            出错信息
	 * @return 空串返回出错信息
	 */
	public static String isEmpty(String input, String errorMsg) {
		if (isEmpty(input)) {
			return errorMsg;
		}
		return input;
	}
	
	/**
	 * 得到文件的扩展名.
	 * 
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the extension portion of the file's name.
	 */
	public static String getExtension(String fileName) {
		if (fileName != null) {
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(i + 1).toLowerCase();
			}
		}
		return "";
	}
	
	/**
	 * 得到文件的前缀名.
	 * 
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the prefix portion of the file's name.
	 */
	public static String getPrefix(String fileName) {
		if (fileName != null) {
			fileName = fileName.replace('\\', '/');
			
			if (fileName.lastIndexOf("/") > 0) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}
			
			int i = fileName.lastIndexOf('.');
			if (i > 0 && i < fileName.length() - 1) {
				return fileName.substring(0, i);
			}
		}
		return "";
	}
	
	/**
	 * 得到文件的短路径, 不保护目录.
	 * 
	 * @param fileName
	 *            需要处理的文件的名字.
	 * @return the short version of the file's name.
	 */
	public static String getShortFileName(String fileName) {
		if (fileName != null) {
			String oldFileName = new String(fileName);
			
			fileName = fileName.replace('\\', '/');
			
			// Handle dir
			if (fileName.endsWith("/")) {
				int idx = fileName.indexOf('/');
				if (idx == -1 || idx == fileName.length() - 1) {
					return oldFileName;
				} else {
					return oldFileName.substring(idx + 1, fileName.length() - 1);
				}
				
			}
			if (fileName.lastIndexOf("/") > 0) {
				fileName = fileName.substring(fileName.lastIndexOf("/") + 1, fileName.length());
			}
			
			return fileName;
		}
		return "";
	}
	
	/**
	 * 
	 * @功能描述：将list中的字符串组合成以str为分隔符的形式，例如xxx,fsd,erwr
	 * @param list
	 *            字符串集合
	 * 
	 * @param str
	 *            要分隔的符号
	 * @return xxx,fsd,erwr形式的字符串
	 */
	public static String getStrSplitWithChar(List<String> list, String str) {
		String result = "";
		if (list == null)
			return result;
		for (int i = 0; i < list.size(); i++) {
			result += list.get(i) + str;
		}
		if (result.endsWith(str)) {
			int pos = result.lastIndexOf(str);
			if (pos > -1)
				result = result.substring(0, pos);
		}
		return result;
	}
	
	/**
	 * 
	 * @功能描述：将list中的字符串组合成以str为分隔符的形式，例如'xxx','fsd','erwr'用户拼接sql条件
	 * @param list
	 *            字符串集合
	 * 
	 * @param str
	 *            要分隔的符号
	 * @return 'xxx','fsd','erwr'形式的字符串
	 */
	public static String getStrSplitWithCharSql(List<String> list, String str) {
		String result = "";
		if (list == null)
			return result;
		for (int i = 0; i < list.size(); i++) {
			result += "'" + ObjUtils.defaultIfNull(list.get(i), "").trim() + "'" + str;
		}
		if (result.endsWith(str)) {
			int pos = result.lastIndexOf(str);
			if (pos > -1)
				result = result.substring(0, pos);
		}
		return result;
	}
	
	public static String toSqlString(String input, String splitWith, String startWith, String endWith) {
		if (isEmpty(input)) {
			return "";
		}
		String[] str = input.split(splitWith);
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length; i++) {
			sb.append(startWith);
			sb.append(str[i]);
			sb.append(endWith);
			sb.append(",");
		}
		int index = sb.lastIndexOf(",");
		if (index != -1) {
			sb.delete(index, sb.length());
		}
		return sb.toString();
	}
	
	/**
	 * 截取字符串
	 * 
	 * @param input
	 *            : 传入字符串
	 * 
	 * @param regex
	 *            : 切割字符串的字符串，或者正则表达式
	 * @param index
	 *            : 切割字符串后的字符串数组，返回改数组的对应下标的字符串
	 * 
	 */
	public static String splitStr(String input, String regex, int index) {
		if (isEmpty(input)) {
			return "";
		} else {
			if (isEmpty(regex))
				regex = "=";
			if (isEmpty(index + ""))
				index = 1;
			String[] temp = input.split(regex);
			if (temp.length == 1)
				return input;
			else
				return temp[index];
		}
	}
	
	public static String spliptListToString(List<Map<String, Object>> list, String column) {
		if (null == list || list.size() == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Map<String, Object> map = null;
		for (int i = 0; i < list.size(); i++) {
			map = list.get(i);
			if (null == map.get(column))
				continue;
			sb.append(map.get(column));
			sb.append(",");
		}
		int index = sb.lastIndexOf(",");
		if (index != -1) {
			sb.delete(index, sb.length());
		}
		return sb.toString();
		
	}
	
	public static int isNullCount(Map<String, Object> map) {
		int count = 0;
		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry entry = (Map.Entry) entries.next();
			Object value = entry.getValue();
			if (isEmpty(value)) {
				count++;
			}
		}
		return count;
	}
	
	/**
	 * 去除Map中value的空格
	 * 
	 * @param map
	 * @return
	 */
	public static Map<String, Object> toTrim(Map<String, Object> map) {
		if (map == null) {
			return null;
		}
		Map<String, Object> m = map;
		Iterator i = m.entrySet().iterator();
		while (i.hasNext()) {
			Map.Entry entry = (Entry) i.next();
			entry.setValue(ObjUtils.defaultIfNull(entry.getValue(), ""));
		}
		return m;
	}
	
	/**
	 * 将list元素转换成“col1,col2”形式
	 * 
	 * @return
	 */
	public static String arrayToString(List<String> s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.size(); i++) {
			if (i != s.size() - 1) {
				sb.append("'" + s.get(i) + "',");
			} else {
				sb.append("'" + s.get(i) + "'");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将list元素转换成“col1,col2”形式，剔除空串的情况
	 * 
	 * @return
	 */
	public static String arrayToString2(List<String> s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.size(); i++) {
			if (s.get(i).equals(""))
				continue;
			if (i != s.size() - 1) {
				sb.append("'" + s.get(i) + "',");
			} else {
				sb.append("'" + s.get(i) + "'");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将list元素转换成“col1,col2”形式，剔除空串的情况,无单引号
	 * 
	 * @return
	 */
	public static String arrayToString3(List<String> s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.size(); i++) {
			if (s.get(i).equals(""))
				continue;
			if (i != s.size() - 1) {
				sb.append(s.get(i) + ",");
			} else {
				sb.append(s.get(i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将字符串数组元素转换成sql字符串“'col1','col2'”形式
	 * 
	 * @return
	 */
	public static String array2Sqlstr(String[] array) {
		StringBuffer sb = new StringBuffer();
		if (array == null || array.length == 0)
			return "''";
		for (int i = 0; i < array.length; i++) {
			if (i != array.length - 1) {
				sb.append("'" + array[i].trim() + "',");
			} else {
				sb.append("'" + array[i].trim() + "'");
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将字符串数组元素转换成sql字符串“'col1','col2'”形式
	 * 
	 * @return
	 */
	public static String array2ProcStr(String[] array) {
		StringBuffer sb = new StringBuffer();
		if (array == null)
			return "";
		for (int i = 0; i < array.length; i++) {
			if (i != array.length - 1) {
				sb.append("''" + array[i].trim() + "'',");
			} else {
				sb.append("''" + array[i].trim() + "''");
			}
		}
		sb.insert(0, "'");
		sb.append("'");
		return sb.toString();
	}
	
	/**
	 * 去除List中Map的value的空格
	 * 
	 * @param map
	 * @return
	 */
	public static List<Map<String, Object>> toTrim(List<Map<String, Object>> map) {
		List<Map<String, Object>> list = map;
		for (int i = 0; i < list.size(); i++) {
			toTrim(list.get(i));
		}
		return list;
	}
	
	/**
	 * 功能描述：根据请求参数（查询条件）将xml中的sql语句重新组装
	 * 
	 * @param parseURL
	 *            ：请求对象
	 * 
	 * @param sql
	 *            ：xml中的sql语句
	 * @return
	 */
	public static String parseSql(Map<String, Object> map, String sql) {
		StringBuffer sb = new StringBuffer();
		/** 截取sql中的查询条件 */
		int i = sql.indexOf("{"); // 截取的起始下标
		
		int j = sql.lastIndexOf("}"); // 截取的末尾下标
		
		if (i == -1 || j == -1)
			return sql;
		/** 将非查询条件的部分添加到StringBuffer中 */
		sb.append(sql.substring(0, i) + " ");
		/** 截取出查询条件 */
		String temp = sql.substring(i + 1, j);
		
		/** 分析查询条件，查询条件中的每个单独的查询条件都包含在()中 */
		
		int index = 0, end = 0; // 临时变量
		String t = ""; // 临时变量
		String r = ""; // 临时变量
		for (int k = 0; k < temp.length(); k++) {
			
			/** 第N个条件的起始位置 */
			if ('(' == temp.charAt(k)) {
				index = k;
			}
			/** 第N个条件的结束位置 */
			if (')' == temp.charAt(k)) {
				end = k;
			}
			/** 如果结束位置的值大于起始位置的值 */
			if (end > index) {
				/** 截取查询条件 */
				t = temp.substring(index + 1, end);
				/** 如果变量为表名，刚去掉表前后的() */
				if (":table:".equals(t)) {
					String s = temp.substring(index, end + 1).replace("(", "").replace(")", "");
					temp = s + temp.substring(end + 1, temp.length());
				}
				/** 替换查询条件中的值 */
				r = replace(map, t);
				
				temp = temp.replace(t, r);
				k = index + r.length() + 2;
				end = 0;
				index = 0;
			}
		}
		sb.append(temp);
		sb.append(sql.substring(j + 1, sql.length()));
		String tmp = sb.toString();
		tmp = tmp.replace("]", ")");
		tmp = tmp.replace("[", "(");
		sb = null;
		temp = null;
		t = null;
		r = null;
		return tmp;
	}
	
	/**
	 * 
	 * 功能描述：替换查询条件中的值
	 * 
	 * @param tmp
	 *            : 被替换的字符串
	 * 
	 * @return
	 */
	public static String replace(Map<String, Object> map, String tmp) {
		Object[] obj = tmp.split(":");
		for (int i = 1; i < obj.length; i += 2) {
			if ("".equals(ObjUtils.trimToString(map.get(obj[i])))) {
				return "1=1";
			} else {
				tmp = tmp.replace(":" + obj[i] + ":", StrUtils.replaceSql(map.get(obj[i])));
			}
		}
		return tmp;
	}
	
	/**
	 * 功能描述：补0操作
	 * 
	 * @param srcStr
	 * @param insertStr
	 * @param len
	 * @return
	 */
	public static String insertStr(String srcStr, String insertStr, int len) {
		String tmp = srcStr;
		int srcLen = ObjUtils.defaultIfNull(tmp, "").length();// 原串长
		
		int insertLen = len - srcLen;
		if (insertLen > 0) {
			for (int i = 0; i < insertLen; i++) {
				tmp = insertStr + tmp;
			}
		}
		return tmp;
	}
	
	/**
	 * 功能描述：清除字符串中所有的空格
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String clearSpace(String srcStr) {
		if (srcStr == null)
			return "";
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(srcStr);
		String after = m.replaceAll("");
		return after;
		
	}
	
	/**
	 * 功能描述：清楚回车换行
	 * 
	 * @param srcStr
	 * @return
	 */
	public static String clearWrap(String srcStr) {
		if (srcStr == null)
			return "";
		Pattern p = Pattern.compile("\\t|\r|\n");
		Matcher m = p.matcher(srcStr);
		String after = m.replaceAll("");
		return after;
	}
	
	public static String replaceHtmlEdit(String inputStr) {
		return clearSpace(inputStr.replace("\"", "\\\""));
	}
	
	/**
	 * 判断字符串是否为浮点型
	 * 
	 * @param String
	 *            str 要判断的字符串
	 * 
	 * @return true 浮点型,false 非浮点型
	 */
	public static boolean isDecimal(String str) {
		if (str == null || "".equals(str))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		return pattern.matcher(str).matches();
	}
	
	/**
	 * 查找字符串数组中是否有匹配值
	 * 
	 * @param String[]
	 *            array 字符串数组
	 * 
	 * @param String
	 *            str 要匹配的值
	 * 
	 * @return 是否存在匹配值
	 * 
	 */
	public static boolean findMatchArray(String[] array, String str) {
		boolean isMatch = false;
		for (int i = 0; i < array.length; i++) {
			if (str.equals(array[i])) {
				isMatch = true;
			}
		}
		return isMatch;
	}
	
	public static String dateFormate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
		
	}
	
	public static Date dateFormate(String date) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(date);
		
	}
	
	public static Date dateFormate2(String date, String mate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(mate);
		return sdf.parse(date);
		
	}
	
	public static String StringPattern(String date, String oldPattern, String newPattern) throws ParseException {
		if (date == null || oldPattern == null || newPattern == null)
			return "";
		SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern); // 实例化模板对象
		SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern); // 实例化模板对象
		Date d = null;
		try {
			d = sdf1.parse(date); // 将给定的字符串中的日期提取出来
		} catch (Exception e) { // 如果提供的字符串格式有错误，则进行异常处理
			e.printStackTrace(); // 打印异常信息
		}
		return sdf2.format(d);
		
	}
	
	// a integer to xx小时xx分xx秒
	public static String secToTime(int time) {
		String timeStr = null;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (time <= 0)
			return "00分00秒";
		else {
			minute = time / 60;
			if (minute < 60) {
				second = time % 60;
				timeStr = unitFormat(minute) + "分" + unitFormat(second) + "秒";
			} else {
				hour = minute / 60;
				if (hour > 99)
					return "99小时59分59秒";
				minute = minute % 60;
				second = time - hour * 3600 - minute * 60;
				timeStr = unitFormat(hour) + "小时" + unitFormat(minute) + "分" + unitFormat(second) + "秒";
			}
		}
		return timeStr;
	}
	
	public static String unitFormat(int i) {
		String retStr = null;
		if (i >= 0 && i < 10)
			retStr = "0" + Integer.toString(i);
		else
			retStr = "" + i;
		return retStr;
	}
	
	/**
	 * 字符串连接
	 * 
	 * @param s
	 * @param delimiter
	 * @return
	 */
	public static String join(Collection<?> s, String delimiter) {
		if (s.isEmpty())
			return "";
		Iterator<?> iter = s.iterator();
		StringBuffer buffer = new StringBuffer(iter.next().toString());
		while (iter.hasNext())
			buffer.append(delimiter).append(iter.next());
		return buffer.toString();
	}
	
	/**
	 * @throws ParseException
	 * 
	 */
	
	public static void main(String[] args) throws ParseException {
		String zz = "2017-06-01 星期五 上午".substring(0, 10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date zzzz = sdf.parse(zz);
		Calendar cal = Calendar.getInstance();
		cal.setTime(zzzz);
		cal.add(Calendar.DATE, 1);
		Date zzz = cal.getTime();
		
		Date date = new Date();
		System.out.println("当前时间：" + date + ";预约时间加1：" + zzz);
		if (date.after(zzz)) {
			System.out.println(111111111);
			
		} else {
			System.out.println(00000000000);
		}
		
	}
	
	/**
	 * @Description
	 * @date 2017年12月23日
	 * @author 邓金
	 * @param stackTrace
	 * @return
	 */
	public static String stackTraceToString(StackTraceElement[] stackTrace) {
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < stackTrace.length; i++) {
			StackTraceElement element = stackTrace[i];
			sb.append(element.toString() + "\n");
		}
		return sb.toString();
	}
	
}
