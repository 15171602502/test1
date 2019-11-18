package com.ysk.source.controller.uit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.source.entity.ProvinceCityarea;
import com.ysk.source.service.ProvinceCityareaSrv;

@Controller
@RequestMapping("/provincecityarea")
public class ProvincecityareaContorller {

	@Resource
	private ProvinceCityareaSrv provinceCityareaSrv;

	/**
	 * 查询省列表
	 * 
	 * @return
	 * @author 江春朋
	 */
	@RequestMapping("/getRegion")
	@ResponseBody
	public Object getRegion() {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		map.put("parentcode", "root");
		List<ProvinceCityarea> regionList = provinceCityareaSrv.selectProvincecityareaByPage(map);
		maps.put("regionList", regionList);
		return maps;
	}

	/**
	 * 获取地区列表
	 * 
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping(value = "/selectRegion/{parentcode}")
	@ResponseBody
	public Object selectRegion(@PathVariable("parentcode") String parentcode) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> maps = new HashMap<String, Object>();
		map.put("parentcode", parentcode);
		maps.put("Region", provinceCityareaSrv.selectProvincecityareaByPage(map));
		return maps;
	}

	@RequestMapping(value = "/selectAddessListByPage")
	@ResponseBody
	public Object selectAddessListByPage() {
		// Map<String,Object> maps=new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("parentcode", "root");
		// 省列表
		List<ProvinceCityarea> regionList = provinceCityareaSrv.selectProvincecityareaByPage(map);
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		for (ProvinceCityarea yskProvincecityarea : regionList) {

			Map<String, Object> resultProvinceMap = new HashMap<String, Object>();
			if (!StrUtils.isEmpty(yskProvincecityarea.getName())) {
				resultProvinceMap.put("value", yskProvincecityarea.getCode());
				resultProvinceMap.put("label", yskProvincecityarea.getName());

				List<Map<String, Object>> resultCityList = new ArrayList<Map<String, Object>>();
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("parentcode", yskProvincecityarea.getCode());
				List<ProvinceCityarea> cityList = provinceCityareaSrv.selectProvincecityareaByPage(map1);

				for (ProvinceCityarea yskcity : cityList) {
					Map<String, Object> resultcityMap = new HashMap<String, Object>();
					if (!StrUtils.isEmpty(yskcity.getName())) {
						resultcityMap.put("value", yskcity.getCode());
						resultcityMap.put("label", yskcity.getName());
						List<Map<String, Object>> resultCountyList = new ArrayList<Map<String, Object>>();
						Map<String, Object> map2 = new HashMap<String, Object>();
						map2.put("parentcode", yskcity.getCode());
						List<ProvinceCityarea> countyList = provinceCityareaSrv.selectProvincecityareaByPage(map2);

						for (ProvinceCityarea yskCounty : countyList) {
							if (!StrUtils.isEmpty(yskCounty.getName())) {
								Map<String, Object> countyMap = new HashMap<String, Object>();
								countyMap.put("value", yskCounty.getCode());
								countyMap.put("label", yskCounty.getName());
								resultCountyList.add(countyMap);
							}
						}
						if (resultCountyList.size() > 0) {

							resultcityMap.put("children", resultCountyList);
						}
						resultCityList.add(resultcityMap);
					}
				}
				resultProvinceMap.put("children", resultCityList);
				resultList.add(resultProvinceMap);
			}
		}
		return resultList;
	}
}
