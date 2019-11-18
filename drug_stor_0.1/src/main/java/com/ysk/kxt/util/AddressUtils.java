package com.ysk.kxt.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

/**
 * 根据IP地址获取详细的地域信息
 * 
 * @author 胡先锋
 *
 */
public class AddressUtils {
	
	/**
	 * 根据IP地址获取详细的地域信息
	 * 
	 * @param content
	 * @param encodingString
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String getAddresses(String content, String encodingString) throws UnsupportedEncodingException {
		// 这里调用pconline的接口
		String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
		// 从http://whois.pconline.com.cn取得IP所在的省市区信息
		String returnStr = this.getResult(urlStr, content, encodingString);
		if (returnStr != null) {
			// 处理返回的省市区信息
			System.out.println(returnStr);
			String[] temp = returnStr.split(",");
			if (temp.length < 3) {
				return "0";//无效IP，局域网测试
			}
			String region = (temp[7].split(":"))[1].replaceAll("\"", "");
			region = decodeUnicode(region);// 省份
			return region;
		}
		return null;
	}
	
	/**
	 * 获取地址
	 * 
	 * @param urlStr
	 * @param content
	 * @param encoding
	 * @return
	 */
	private String getResult(String urlStr, String content, String encoding) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();// 新建连接实例
			connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
			connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
			connection.setDoOutput(true);// 是否打开输出流 true|false
			connection.setDoInput(true);// 是否打开输入流true|false
			connection.setRequestMethod("POST");// 提交方法POST|GET
			connection.setUseCaches(false);// 是否缓存true|false
			connection.connect();// 打开连接端口
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());// 打开输出流往对端服务器写数据
			out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
			out.flush();// 刷新
			out.close();// 关闭输出流
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
			// ,以BufferedReader流来读取
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();// 关闭连接
			}
		}
		return null;
	}
	
	/**
	 * uniCode 转换成 中文
	 * 
	 * @param theString
	 * @return
	 */
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
							case '0':
							case '1':
							case '2':
							case '3':
							case '4':
							case '5':
							case '6':
							case '7':
							case '8':
							case '9':
								value = (value << 4) + aChar - '0';
								break;
							case 'a':
							case 'b':
							case 'c':
							case 'd':
							case 'e':
							case 'f':
								value = (value << 4) + 10 + aChar - 'a';
								break;
							case 'A':
							case 'B':
							case 'C':
							case 'D':
							case 'E':
							case 'F':
								value = (value << 4) + 10 + aChar - 'A';
								break;
							default:
								throw new IllegalArgumentException("Malformed      encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't') {
						aChar = '\t';
					} else if (aChar == 'r') {
						aChar = '\r';
					} else if (aChar == 'n') {
						aChar = '\n';
					} else if (aChar == 'f') {
						aChar = '\f';
					}
					outBuffer.append(aChar);
				}
			} else {
				outBuffer.append(aChar);
			}
		}
		return outBuffer.toString();
	}
	
	/**
	 * 获取访问ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				int index = ip.indexOf(",");
				if (index != -1) {
					return ip.substring(0, index);
				} else {
					return ip;
				}
			}
		}
		ip = request.getHeader("X-Real-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getHeader("Proxy-Client-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip != null) {
			if (!ip.isEmpty() && !"unKnown".equalsIgnoreCase(ip)) {
				return ip;
			}
		}
		ip = request.getRemoteAddr();
		return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
	}
	
	public static String getRemortMac(HttpServletRequest request) throws Exception {
		String mac = null;
		try {
			Process pro = Runtime.getRuntime().exec("cmd.exe /c ipconfig/all");
			
			InputStream is = pro.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String message = br.readLine();
			
			int index = -1;
			while (message != null) {
				if ((index = message.indexOf("Physical Address")) > 0) {
					mac = message.substring(index + 36).trim();
					break;
				}
				message = br.readLine();
			}
			System.out.println(mac);
			br.close();
			pro.destroy();
		} catch (IOException e) {
			System.out.println("Can't get mac address!");
			return null;
		}
		return mac;
	}
	
	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(provinceForShort("湖北省"));
	}
	
	public static String provinceForShort(String province) {
		
		if (province.equals("北京市"))
			return "京";
		else if (province.equals("天津市"))
			return "津";
		else if (province.equals("重庆市"))
			return "渝";
		else if (province.equals("上海市"))
			return "沪";
		else if (province.equals("河北省"))
			return "冀";
		else if (province.equals("山西省"))
			return "晋";
		else if (province.equals("辽宁省"))
			return "辽";
		else if (province.equals("吉林省"))
			return "吉";
		else if (province.equals("黑龙江省"))
			return "黑";
		else if (province.equals("江苏省"))
			return "苏";
		else if (province.equals("浙江省"))
			return "浙";
		else if (province.equals("安徽省"))
			return "皖";
		else if (province.equals("福建省"))
			return "闽";
		else if (province.equals("江西省"))
			return "赣";
		else if (province.equals("山东省"))
			return "鲁";
		else if (province.equals("河南省"))
			return "豫";
		else if (province.equals("湖北省"))
			return "鄂";
		else if (province.equals("湖南省"))
			return "湘";
		else if (province.equals("广东省"))
			return "粤";
		else if (province.equals("海南省"))
			return "琼";
		else if (province.equals("四川省"))
			return "川/蜀";
		else if (province.equals("贵州省"))
			return "黔/贵";
		else if (province.equals("云南省"))
			return "云/滇";
		else if (province.equals("陕西省"))
			return "陕/秦";
		else if (province.equals("甘肃省"))
			return "甘/陇";
		else if (province.equals("青海省"))
			return "青";
		else if (province.equals("台湾省"))
			return "台";
		else if (province.equals("内蒙古自治区"))
			return "内蒙古";
		else if (province.equals("广西壮族自治区"))
			return "桂";
		else if (province.equals("宁夏回族自治区"))
			return "宁";
		else if (province.equals("新疆维吾尔自治区 "))
			return "新";
		else if (province.equals("西藏自治区"))
			return "藏";
		else if (province.equals("香港特别行政区"))
			return "港";
		else if (province.equals("澳门特别行政区"))
			return "澳";
		else {
			
			return province;
		}
		
	}
	/*
	 * public static String getRegionByMobileNo(String mobileNo) throws Exception{
	 * String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile=%s";
	 * url = String.format(url, mobileNo); Document doc = Jsoup.connect(url).get();
	 * Elements els = doc.getElementsByClass("tdc2"); return els.get(1).text(); }
	 */
}