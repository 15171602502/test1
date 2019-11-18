package com.ysk.source.controller.mobileSoftware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysk.kxt.sourceUit.ResultUit;
import com.ysk.kxt.util.string.StrUtils;
import com.ysk.source.entity.CollectRecords;
import com.ysk.source.entity.DrugInformation;
import com.ysk.source.entity.PharmacyInformation;
import com.ysk.source.service.CollectRecordsSrv;
import com.ysk.source.service.DrugInformationSrv;
import com.ysk.source.service.PharmacyInformationSrv;

/**
 * 用户收藏信息相关接口
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping(value = "/collect")
public class CollectController {

	@Resource
	private CollectRecordsSrv collectRecordsSrv;
	@Resource
	private PharmacyInformationSrv pharmacyInformationSrv;
	@Resource
	private DrugInformationSrv drugInformationSrv;

	/**
	 * 用户添加药店关注
	 * 
	 * @param userId
	 *            用户id
	 * @param pharmacyId
	 *            药店id
	 * @return
	 */
	@RequestMapping(value = "/addCollectPharmacy")
	@ResponseBody
	public Object addCollectPharmacy(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String pharmacyId) {

		CollectRecords resultCR = collectRecordsSrv.selectWhetherCollectByuserId(userId, 2, pharmacyId);
		if (!StrUtils.isEmpty(resultCR)) {
			System.out.println("该药店已关注，不能重复关");
			return new ResultUit("10010", "该药店已关注，不能重复关注");
		} else {
			CollectRecords crInfo = new CollectRecords();

			crInfo.setUserId(userId);
			crInfo.setCollectType(2);
			crInfo.setProductId(pharmacyId);
			if (collectRecordsSrv.insert(crInfo) > 0) {
				return new ResultUit("10000", "关注成功");
			} else {
				return new ResultUit("10010", "关注失败");
			}
		}
	}

	/**
	 * 用户取消药店关注
	 * 
	 * @param userId
	 *            用户ID
	 * @param productId
	 *            药店ID
	 * @return
	 */
	@RequestMapping(value = "/deleteCollectPharmacy")
	@ResponseBody
	public Object deleteCollectPharmacy(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String pharmacyId) {

		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("userId", userId);
		mapInfo.put("collectType", 2);
		mapInfo.put("productId", pharmacyId);
		if (collectRecordsSrv.deleteCollectPharmacy(mapInfo) > 0) {
			return new ResultUit("10000", "取消成功");
		} else {
			return new ResultUit("10010", "取消失败");
		}
	}

	/**
	 * 用户添加药品收藏
	 * 
	 * @param userId
	 *            用户id
	 * @param pharmacyId
	 *            药店id
	 * @return
	 */
	@RequestMapping(value = "/addCollectDrug")
	@ResponseBody
	public Object addCollectDrug(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String drugId) {

		CollectRecords resultCR = collectRecordsSrv.selectWhetherCollectByuserId(userId, 1, drugId);
		if (!StrUtils.isEmpty(resultCR)) {
			System.out.println("该药品已收藏，不能重复收藏");
			return new ResultUit("10010", "该药品已收藏，不能重复收藏");
		} else {
			CollectRecords crInfo = new CollectRecords();

			crInfo.setUserId(userId);
			crInfo.setCollectType(1);
			crInfo.setProductId(drugId);
			if (collectRecordsSrv.insert(crInfo) > 0) {
				return new ResultUit("10000", "收藏成功");
			} else {
				return new ResultUit("10010", "收藏失败");
			}
		}
	}

	/**
	 * 用户取消药品收藏
	 * 
	 * @param userId
	 *            用户ID
	 * @param productId
	 *            药店ID
	 * @return
	 */
	@RequestMapping(value = "/deleteCollectDrug")
	@ResponseBody
	public Object deleteCollectDrug(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String drugId) {

		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("userId", userId);
		mapInfo.put("collectType", 1);
		mapInfo.put("productId", drugId);
		if (collectRecordsSrv.deleteCollectPharmacy(mapInfo) > 0) {
			return new ResultUit("10000", "取消成功");
		} else {
			return new ResultUit("10010", "取消失败");
		}
	}

	/**
	 * 查询用户收藏列表
	 * 
	 * @param userId
	 *            用户ID
	 * @param collectType
	 *            收藏类型:1、药品；2、药店
	 * @return
	 */
	@RequestMapping(value = "/getCollectListByUserId")
	@ResponseBody
	public Object getCollectListByUserId(@RequestParam(required = true) String userId,
			@RequestParam(value = "collectType", defaultValue = "2") Integer collectType) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("userId", userId);
		mapInfo.put("collectType", collectType);
		List<CollectRecords> resuliList = collectRecordsSrv.getCollectListByUserId(mapInfo);
		// 药品收藏列表
		if (collectType == 1) {
			List<DrugInformation> drugList = new ArrayList<DrugInformation>();
			for (CollectRecords crs1 : resuliList) {
				if (!crs1.getProductId().equals("") && !StrUtils.isEmpty(crs1.getProductId())) {
					DrugInformation dpiInfo = drugInformationSrv.selectByPrimaryKey(crs1.getProductId());
					if (!StrUtils.isEmpty(dpiInfo)) {
						drugList.add(dpiInfo);
					}
				}
			}
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("drugList", drugList);
			return new ResultUit("10000", "请求成功", map1);
			// 药店关注列表
		} else if (collectType == 2) {
			List<PharmacyInformation> pharmacyList = new ArrayList<PharmacyInformation>();
			for (CollectRecords crs2 : resuliList) {
				if (!crs2.getProductId().equals("") && !StrUtils.isEmpty(crs2.getProductId())) {
					PharmacyInformation piInfo = pharmacyInformationSrv.selectByPrimaryKey(crs2.getProductId());
					if (!StrUtils.isEmpty(piInfo)) {
						pharmacyList.add(piInfo);
					}
				}
			}
			Map<String, Object> map2 = new HashMap<String, Object>();
			map2.put("pharmacyList", pharmacyList);
			return new ResultUit("10000", "请求成功", map2);
		} else {
			return new ResultUit("10010", "请求失败");
		}
	}

}
