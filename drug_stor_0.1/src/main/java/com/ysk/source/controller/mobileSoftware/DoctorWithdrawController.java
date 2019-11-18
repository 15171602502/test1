package com.ysk.source.controller.mobileSoftware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.ysk.kxt.sourceUit.PageBean;
import com.ysk.kxt.sourceUit.ResultUit;
import com.ysk.kxt.util.string.StrUtils;
import com.ysk.source.entity.DoctorWithdraw;
import com.ysk.source.entity.FansPurchaseRecord;
import com.ysk.source.entity.UserInfo;
import com.ysk.source.service.DoctorWithdrawSrv;
import com.ysk.source.service.FansPurchaseRecordSrv;
import com.ysk.source.service.UserInfoSrv;

/**
 * 医生提现相关接口
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping(value = "/doctorWithdraw")
public class DoctorWithdrawController {

	@Resource
	private DoctorWithdrawSrv DoctorWithdrawSrv;
	@Resource
	private FansPurchaseRecordSrv fansPurchaseRecordSrv;
	@Resource
	private UserInfoSrv userInfoSrv;

	/**
	 * 医生最近收入
	 * 
	 * @param doctorId
	 *            医生id
	 * @param settlementState
	 *            结算状态 1、待结算；2、已结算
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getLateyIncomeByDoctorId")
	@ResponseBody
	public Object getLateyIncomeByDoctorId(@RequestParam(required = true) String doctorId,
			@RequestParam(required = true) Integer settlementState,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("doctorId", doctorId);
		mapInfo.put("settlementState", settlementState);
		PageHelper.startPage(pageNum, pageSize);
		List<FansPurchaseRecord> fprlist = fansPurchaseRecordSrv.selectRecordByDoctorId(mapInfo);
		PageBean<FansPurchaseRecord> listPage = new PageBean<FansPurchaseRecord>(pageNum, pageSize, fprlist);
		resultMap.put("incomeList", fprlist);
		resultMap.put("page", listPage);
		resultMap.put("withdraw", 15120);//医生余额
		return new ResultUit("10000", "请求成功", resultMap);
	}

	/**
	 * 医生提现记录
	 * 
	 * @param doctorId
	 *            医生id
	 * @param withdrawalType
	 *            提现状态
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getWithdrawByDoctorId")
	@ResponseBody
	public Object getWithdrawByDoctorId(@RequestParam(required = true) String doctorId,
			@RequestParam(required = true) Integer withdrawalType,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("doctorId", doctorId);
		mapInfo.put("withdrawalType", withdrawalType);

		PageHelper.startPage(pageNum, pageSize);
		List<DoctorWithdraw> dwList = DoctorWithdrawSrv.selectAllByPage(mapInfo);
		PageBean<DoctorWithdraw> listPage = new PageBean<DoctorWithdraw>(pageNum, pageSize, dwList);
		resultMap.put("withdrawalList", dwList);
		resultMap.put("page", listPage);
		return new ResultUit("10000", "请求成功", resultMap);
	}

	/**
	 * 医生粉丝列表
	 * 
	 * @param doctorId
	 *            医生id
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getFansListByDoctorId")
	@ResponseBody
	public Object getFansListByDoctorId(@RequestParam(required = true) String doctorId,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		UserInfo doctor = userInfoSrv.selectByPrimaryKey(doctorId);
		if (!StrUtils.isEmpty(doctor)) {
			// 查询粉丝
			PageHelper.startPage(pageNum, pageSize);
			List<UserInfo> fansList = userInfoSrv.selectFansByDoctorId(doctorId);
			PageBean<UserInfo> listPage = new PageBean<UserInfo>(pageNum, pageSize, fansList);

			resultMap.put("fansList", fansList);
			resultMap.put("page", listPage);
			return new ResultUit("10000", "请求成功", resultMap);
		} else {
			return new ResultUit("10010", "医生信息为空");
		}
	}
}
