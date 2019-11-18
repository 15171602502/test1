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

import com.github.pagehelper.PageHelper;
import com.mysql.fabric.xmlrpc.base.Array;
import com.ysk.kxt.sourceUit.PageBean;
import com.ysk.kxt.sourceUit.ResultUit;
import com.ysk.kxt.util.string.StrUtils;
import com.ysk.source.entity.CollectRecords;
import com.ysk.source.entity.DrugInformation;
import com.ysk.source.entity.PharmacyInformation;
import com.ysk.source.entity.ProvinceCityarea;
import com.ysk.source.service.CollectRecordsSrv;
import com.ysk.source.service.PharmacyInformationSrv;
import com.ysk.source.service.ProvinceCityareaSrv;
import com.ysk.source.service.DrugInformationSrv;

/**
 * 药店相关信息相关接口
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping(value = "/drugstore")
public class DrugStoreController {

	@Resource
	private PharmacyInformationSrv pharmacyInformationSrv;
	@Resource
	private DrugInformationSrv drugInformationSrv;
	@Resource
	private CollectRecordsSrv collectRecordsSrv;
	@Resource
	private ProvinceCityareaSrv provinceCityareaSrv;

	/**
	 * 获取附近药店信息
	 * 
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @param distance
	 *            范围
	 * @param pageNum
	 *            当前页数
	 * @param pageSize
	 *            每页条数
	 * @return
	 */
	@RequestMapping(value = "/getNearbyDrugstore")
	@ResponseBody
	public Object getNearbyDrugstore(@RequestParam(required = true) Float longitude,
			@RequestParam(required = true) Float latitude,
			@RequestParam(value = "distance", defaultValue = "1000") Integer distance,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> locaMap = new HashMap<String, Object>();
		PageHelper.startPage(pageNum, pageSize);

		locaMap.put("longitude", longitude);
		locaMap.put("latitude", latitude);
		locaMap.put("distance", distance);
		List<PharmacyInformation> listInfo = pharmacyInformationSrv.selectPharmacyByLocation(locaMap);
		PageBean<PharmacyInformation> listPage = new PageBean<PharmacyInformation>(pageNum, pageSize, listInfo);
		// 查询是否收藏
		if (!StrUtils.isEmpty(userId)) {
			for (PharmacyInformation pharmacyInformation : listInfo) {
				CollectRecords collectInfo = collectRecordsSrv.selectWhetherCollectByuserId(userId, 2,
						pharmacyInformation.getPharmacyId());
				if (!StrUtils.isEmpty(collectInfo)) {
					pharmacyInformation.setAttentionState(1);
				}
			}
		}

		resultMap.put("pharmacyList", listInfo);
		resultMap.put("page", listPage);

		return new ResultUit("10000", "请求成功", resultMap);
	}

	/**
	 * 搜索药品信息
	 * 
	 * @param searchCriteria
	 *            搜索条件
	 * @return
	 */
	@RequestMapping(value = "/searchDrugByPage")
	@ResponseBody
	public Object searchDrugByPage(@RequestParam(required = true) String searchCriteria,
			@RequestParam(value = "searchType", defaultValue = "1") Integer searchType) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 药品搜索
		if (searchType == 1) {
			List<Object> listInfo = new ArrayList<Object>();
			Map<String, Object> drugMap = new HashMap<String, Object>();
			drugMap.put("drugName", searchCriteria);
			List<DrugInformation> drugList = drugInformationSrv.selectBysearch(drugMap);
			for (DrugInformation drugInformation : drugList) {
				PharmacyInformation pharmacy = pharmacyInformationSrv
						.selectByPrimaryKey(drugInformation.getPharmacyId());
				List<DrugInformation> list1 = new ArrayList<DrugInformation>();
				list1.add(drugInformation);
				pharmacy.setDrugList(list1);
				listInfo.add(pharmacy);
			}
			resultMap.put("pharmacyList", listInfo);
			return new ResultUit("10000", "请求成功", resultMap);

		} else if (searchType == 2) {
			List<PharmacyInformation> resultList=new ArrayList<PharmacyInformation>();
			List<ProvinceCityarea> cityList = provinceCityareaSrv.selectProvincecityareaBySearch(searchCriteria);
			for (ProvinceCityarea provinceCityarea : cityList) {
				if (!StrUtils.isEmpty(provinceCityarea.getCode())) {
					List<PharmacyInformation> infoList = pharmacyInformationSrv
							.selectPharmacyByCityCode(provinceCityarea.getCode());
					for (PharmacyInformation pharmacyInformation : infoList) {
						resultList.add(pharmacyInformation);
					}
				}
			}
			resultMap.put("pharmacyList", resultList);
			return new ResultUit("10000", "请求成功", resultMap);
		} else {
			return new ResultUit("10010", "请正确选择搜索条件");
		}
	}

	/**
	 * 获取药店详情
	 * 
	 * @param pharmacyId
	 *            药店id
	 * @param userId
	 *            用户id
	 * @return
	 */
	@RequestMapping(value = "/getDrugstoreDetails")
	@ResponseBody
	public Object getDrugstoreDetails(@RequestParam(required = true) String pharmacyId, String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		PharmacyInformation pharmacyInfo = pharmacyInformationSrv.selectByPrimaryKey(pharmacyId);
		// 查询是否收藏
		if (!StrUtils.isEmpty(userId)) {
			CollectRecords collectInfo = collectRecordsSrv.selectWhetherCollectByuserId(userId, 2, pharmacyId);
			if (!StrUtils.isEmpty(collectInfo)) {
				pharmacyInfo.setAttentionState(1);
			}
		}

		resultMap.put("pharmacyInfo", pharmacyInfo);
		List<DrugInformation> druglist = drugInformationSrv.selectByPharmacyId(pharmacyId);

		if (!StrUtils.isEmpty(userId)) {
			for (DrugInformation drugInformation : druglist) {
				CollectRecords collectInfo = collectRecordsSrv.selectWhetherCollectByuserId(userId, 1,
						drugInformation.getDrugId());
				if (!StrUtils.isEmpty(collectInfo)) {
					drugInformation.setAttentionDrug(1);
				}
			}
		}
		resultMap.put("drugList", druglist);
		return new ResultUit("10000", "请求成功", resultMap);
	}

}
