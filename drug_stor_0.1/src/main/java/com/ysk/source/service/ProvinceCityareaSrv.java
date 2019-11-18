package com.ysk.source.service;

import java.util.List;
import java.util.Map;

import com.ysk.source.entity.ProvinceCityarea;

public interface ProvinceCityareaSrv {

	/**
	 * 添加自定义城市
	 */
	public int insert(ProvinceCityarea record);

	/**
	 * 查询所有城市
	 */
	public List<ProvinceCityarea> selectAll();

	/**
	 * 根据条件查询城市列表
	 */
	public List<ProvinceCityarea> selectProvincecityareaByPage(Map<String, Object> maps);
	
	/**
	 * 根据城市名称模糊查询城市列表
	 */
	public List<ProvinceCityarea> selectProvincecityareaBySearch(String searchCriteria);
	
	/**
	 * 根据条件查询城市
	 */
	public ProvinceCityarea selectProvincecityareaById(ProvinceCityarea provincecityarea);

	/**
	 * 根据编号查询城市
	 */
	public ProvinceCityarea selectProvincecityareaByCode(String code);

	/**
	 * 修改城市信息
	 */
	public int updateProvincecityareaById(ProvinceCityarea provincecityarea);

	/**
	 * 删除城市信息
	 */
	public int deleteProvincecityareaById(String id);
}
