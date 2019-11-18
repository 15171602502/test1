package com.ysk.source.controller.mobileSoftware;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysk.kxt.sourceUit.ResultUit;
import com.ysk.kxt.util.string.StrUtils;
import com.ysk.source.entity.CollectRecords;
import com.ysk.source.entity.DrugDetails;
import com.ysk.source.entity.DrugInformation;
import com.ysk.source.service.CollectRecordsSrv;
import com.ysk.source.service.DrugInformationSrv;
import com.ysk.source.service.PharmacyInformationSrv;
import com.ysk.source.service.DrugDetailsSrv;

/**
 * 药品相关接口
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping(value = "/drug")
public class DrugController {
	
	@Resource
	private DrugInformationSrv drugInformationSrv;
	@Resource
	private DrugDetailsSrv drugDetailsSrv;
	@Resource
	private CollectRecordsSrv collectRecordsSrv;
	@Resource
	private PharmacyInformationSrv pharmacyInformationSrv;

	@RequestMapping(value = "/test")
	@ResponseBody
	public Object getDrugInfoDetails() {
		return 1;
	}
	
	/**
	 * 获取药品详情
	 * 
	 * @param drugId
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/getDrugInfoDetails")
	@ResponseBody
	public Object getDrugInfoDetails(@RequestParam(required = true) String drugId, String userId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		DrugInformation drugInfo = drugInformationSrv.selectByPrimaryKey(drugId);
		if (!StrUtils.isEmpty(userId)) {
			CollectRecords collectInfo = collectRecordsSrv.selectWhetherCollectByuserId(userId, 1, drugId);
			if (!StrUtils.isEmpty(collectInfo)) {
				drugInfo.setAttentionDrug(1);
			}
		}
		//药品信息
		resultMap.put("drugInfo", drugInfo);
		System.out.println(drugInfo.getDrugId());
		//药店名称
		resultMap.put("pharmacyName", pharmacyInformationSrv.selectByPrimaryKey(drugInfo.getPharmacyId()).getPharmacyName());
		// 详情
		DrugDetails drugDetails = drugDetailsSrv.selectByPrimaryKey(drugId);
		resultMap.put("drugDetails", drugDetails);

		return new ResultUit("10000", "请求成功", resultMap);
	}

}
