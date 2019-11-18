package com.ysk.source.controller.mobileSoftware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.ysk.kxt.sourceUit.PageBean;
import com.ysk.kxt.sourceUit.ResultUit;
import com.ysk.kxt.util.string.StrUtils;
import com.ysk.source.entity.LoginLog;
import com.ysk.source.entity.UserInfo;
import com.ysk.source.service.LoginLogSrv;
import com.ysk.source.service.UserInfoSrv;
import com.ysk.source.service.ValidateCodeSrv;

/**
 * 用户相关操作相关接口
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping(value = "/userInfo")
public class UserInfoController {

	@Resource
	private UserInfoSrv userInfoSrv;
	@Autowired
	private ValidateCodeSrv validateCodeSrv;
	@Autowired
	private LoginLogSrv loginLogSrv;

	/**
	 * 查询所有的用户信息（测试接口）
	 * 
	 * @return
	 * 
	 * 
	 * @author
	 */
	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public Object selectAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

		Map<String, Object> maps = new HashMap<String, Object>();

		PageHelper.startPage(pageNum, pageSize);
		List<UserInfo> list = userInfoSrv.selectAll();

		PageBean<UserInfo> listPage = new PageBean<UserInfo>(pageNum, pageSize, list);
		maps.put("list", list);
		maps.put("page", listPage);
		// PageInfo<UserInfo> pageInfo = new PageInfo<>(list);

		// ResultUit result = new ResultUit("123456", "dfsfdsdfs",
		// "23132154654");
		// return result.sussess(maps);
		return new ResultUit("10000", "请求成功", maps);
		// return listPage;
	}

	/**
	 * 用户注册
	 * 
	 * @param phone
	 *            用户手机号
	 * @param verificationCode
	 *            短信验证码
	 * @param invitationCode
	 *            医生邀请码
	 * @return
	 */
	@RequestMapping(value = "/userRegister")
	@ResponseBody
	public Object userRegister(@RequestParam(required = true) String phone,
			@RequestParam(required = true) String verificationCode,
			@RequestParam(required = true) String invitationCode) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();
		// 医生注册邀请码常量
		String doctorCode = "201905";

		if (StrUtils.isEmpty(verificationCode)) {
			return new ResultUit("10010", "短信验证码不能为空");
		}
		if (StrUtils.isEmpty(invitationCode)) {
			return new ResultUit("10010", "邀请码不能为空");
			// 判断是否是医生注册
		} else if (!invitationCode.equals(doctorCode)) {
			// 查询医生推荐码是否存在
			List<UserInfo> doctorList = userInfoSrv.selectInvitationCode(invitationCode);
			if (doctorList.size() < 1) {
				return new ResultUit("10000", "邀请码不存在，请重新确认");
			}
		}
		UserInfo userResult = userInfoSrv.userLoginByPhone(phone);
		if (!StrUtils.isEmpty(userResult)) {
			return new ResultUit("10010", "该手机号已经注册，请勿重复注册");
		} else {
			if (validateCodeSrv.validate(verificationCode, phone) == 1) {
				UserInfo userInfo = new UserInfo();
				userInfo.setPhone(phone);
				userInfo.setReferrer(invitationCode);
				// 判断是否是医生注册
				if (invitationCode.equals(doctorCode)) {
					// 医生推荐码
					String second;
					boolean flag = true;
					do {
						int n = 0;
						while (n < 100000) {
							n = (int) (Math.random() * 1000000);
						}
						second = String.valueOf(n);

						List<UserInfo> codeList = userInfoSrv.selectInvitationCode(second);
						if (codeList.size() < 1) {
							flag = false;
						}
					} while (flag);
					userInfo.setInvitationCode(second);
					userInfo.setUserType(1);
				} else {
					userInfo.setUserType(0);
				}
				if (userInfoSrv.insertUser(userInfo) > 0) {
					return new ResultUit("10000", "注册成功");
				} else {
					return new ResultUit("10010", "注册失败");
				}
			} else {
				return new ResultUit("10010", "短信验证码不匹配");
			}
		}
	}

	/**
	 * 用户登录
	 * 
	 * @param phone
	 *            用户手机号
	 * @param verificationCode
	 *            短信验证码
	 * @return
	 */
	@RequestMapping(value = "/userLogin")
	@ResponseBody
	public Object userLogin(@RequestParam(required = true) String phone,
			@RequestParam(required = true) String verificationCode) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		if (validateCodeSrv.validate(verificationCode, phone) == 1) {
			userInfoSrv.userLoginByPhone(phone);

			UserInfo userResult = userInfoSrv.userLoginByPhone(phone);
			if (!StrUtils.isEmpty(userResult)) {
				LoginLog userLogin = new LoginLog();
				userLogin.setUserId(userResult.getUserId());
				loginLogSrv.insert(userLogin);

				return new ResultUit("10000", "登录成功", userResult);
			} else {
				return new ResultUit("10010", "登录失败，请检查手机号是否正确");
			}
		} else {
			/*  正常流程，测试阶段先隐藏
			 *  return new ResultUit("10010", "短信验证码不匹配"); 
			 */
			
			//测试临时用，过后删除
			userInfoSrv.userLoginByPhone(phone);

			UserInfo userResult = userInfoSrv.userLoginByPhone(phone);
			if (!StrUtils.isEmpty(userResult)) {
				LoginLog userLogin = new LoginLog();
				userLogin.setUserId(userResult.getUserId());
				loginLogSrv.insert(userLogin);

				return new ResultUit("10000", "登录成功", userResult);
			} else {
				return new ResultUit("10010", "登录失败，请检查手机号是否正确");
			}
		}
	}

	/**
	 * 获取用户个人信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public Object getUserInfo(@RequestParam(required = true) String userId) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		UserInfo user = userInfoSrv.selectByPrimaryKey(userId);
		if (!StrUtils.isEmpty(user)) {
			return new ResultUit("10000", "请求成功", user);
		} else {
			return new ResultUit("10010", "请求失败");
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @param userId
	 *            用户id
	 * @param nickName
	 *            昵称
	 * @param sex
	 *            性别 0、男；1、女
	 * @param birthday
	 *            生日
	 * @param headPortrait
	 *            头像
	 * @param aliPay
	 *            支付宝
	 * @return
	 */
	@RequestMapping(value = "/getNearbyDrugstore")
	@ResponseBody
	public Object getNearbyDrugstore(@RequestParam(required = true) String userId, String nickName, Integer sex,
			String birthday, String headPortrait, String aliPay) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		UserInfo user = new UserInfo();
		user.setUserId(userId);
		user.setNickName(nickName);
		user.setSex(sex);
		user.setBirthday(birthday);
		user.setHeadPortrait(headPortrait);
		user.setAlipay(aliPay);
		if (userInfoSrv.updateByPrimaryKey(user) > 0) {
			return new ResultUit("10000", "请求成功");
		} else {
			return new ResultUit("10010", "请求失败");
		}
	}

}
