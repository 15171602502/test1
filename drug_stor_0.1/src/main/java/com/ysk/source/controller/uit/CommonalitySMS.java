package com.ysk.source.controller.uit;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysk.source.entity.ValidateCode;
import com.ysk.source.service.ValidateCodeSrv;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ysk.kxt.util.httpclient.HttpAPIService;

/**
 * 短信验证
 * 
 * @author admin
 *
 */

@RestController
@RequestMapping(value = "/verification")
public class CommonalitySMS {

	@Autowired
	private HttpAPIService HttpAPIService;
	@Autowired
	private ValidateCodeSrv validateCodeSrv;

	static Map<String, String> datemap = new HashMap<String, String>();

	/**
	 * 获取用户注册短信验证码
	 * 
	 * @param mobileNo
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendVerificationCode")
	@ResponseBody
	public Object sendVerificationCode(@RequestParam(required = true) String phone) throws Exception {

		Map<String, String> map = new HashMap<String, String>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d1 = datemap.get(phone);
		if (d1 != null) {
			int index = (int) (new Date().getTime() - sdf.parse(d1).getTime()) / 1000;
			if (index < 90) {
				map.put("resultCode", "10000");
				map.put("resultMsg", "请勿频繁获取验证码!请在2分钟后重试!");
				return map;
			}
		}
		ValidateCode code = new ValidateCode();
		code.setMobileNo(phone);
		int n = 0;
		while (n < 100000) {
			n = (int) (Math.random() * 1000000);
		}
		String second = String.valueOf(n);
		code.setCode(second);
		if (validateCodeSrv.insertOrUpdateValateCode(code) > 0) {

			String str = null;
			String url = "http://v.juhe.cn/sms/send";
			Map<String, Object> ValidateMap = new HashMap<String, Object>();
			ValidateMap.put("mobile", phone);
			ValidateMap.put("tpl_id", 111354);
			ValidateMap.put("tpl_value", "#code#=" + second);
			ValidateMap.put("key", "a1a2f8b334adea73c4de5c848848959b");
			ValidateMap.put("dtype", "json");
			str = HttpAPIService.doGet(url, ValidateMap);

			JSONObject js = JSON.parseObject(str);
			String error_code = js.get("error_code").toString();// 返回的状态码
			if (error_code.equals("0")) {
				datemap.put(phone, sdf.format(new Date()));
				// map.put("returns", "success");
				map.put("resultCode", "10000");
				map.put("resultMsg", "发送成功");
			} else {
				// map.put("returns", "false");
				map.put("resultCode", "10010");
				map.put("resultMsg", "发送失败");
			}
		} else {
			// map.put("returns", "false");
			map.put("resultCode", "10010");
			map.put("resultMsg", "发送失败");
		}
		return map;

	}

	/**
	 * 用户登录短信验证码
	 * 
	 * @param mobileNo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendLoginCode")
	@ResponseBody
	public Object sendLoginCode(@RequestParam(required = true) String phone) throws Exception {

		Map<String, String> map = new HashMap<String, String>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String d1 = datemap.get(phone);
		if (d1 != null) {
			int index = (int) (new Date().getTime() - sdf.parse(d1).getTime()) / 1000;
			if (index < 90) {
				map.put("resultCode", "10000");
				map.put("resultMsg", "请勿频繁获取验证码!请在2分钟后重试!");
				return map;
			}
		}
		ValidateCode code = new ValidateCode();
		code.setMobileNo(phone);
		int n = 0;
		while (n < 100000) {
			n = (int) (Math.random() * 1000000);
		}
		String second = String.valueOf(n);
		code.setCode(second);
		if (validateCodeSrv.insertOrUpdateValateCode(code) > 0) {

			String str = null;
			String url = "http://v.juhe.cn/sms/send";
			Map<String, Object> ValidateMap = new HashMap<String, Object>();
			ValidateMap.put("mobile", phone);
			ValidateMap.put("tpl_id", 111354);
			ValidateMap.put("tpl_value", "#code#=" + second);
			ValidateMap.put("key", "a1a2f8b334adea73c4de5c848848959b");
			ValidateMap.put("dtype", "json");
			str = HttpAPIService.doGet(url, ValidateMap);

			JSONObject js = JSON.parseObject(str);
			String error_code = js.get("error_code").toString();// 返回的状态码
			if (error_code.equals("0")) {
				datemap.put(phone, sdf.format(new Date()));
				// map.put("returns", "success");
				map.put("resultCode", "10000");
				map.put("resultMsg", "发送成功");
			} else {
				// map.put("returns", "false");
				map.put("resultCode", "10010");
				map.put("resultMsg", "发送失败");
			}
		} else {
			// map.put("returns", "false");
			map.put("resultCode", "10010");
			map.put("resultMsg", "发送失败");
		}
		return map;

	}

	/**
	 * 短信验证
	 * 
	 * @return
	 */
	@RequestMapping("/validate")
	@ResponseBody
	public Object valiate(@RequestParam(required = true) String phone,
			@RequestParam(required = true) String validataCode) {
		Map<String, String> map = new HashMap<String, String>();
		try {
			if (validateCodeSrv.validate(validataCode, phone) == 1) {
				map.put("returns", "success");
			} else {
				map.put("returns", "false");
			}
		} catch (Exception e) {
			map.put("returns", "false");
		}
		return map;
	}

}
