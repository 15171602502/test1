package com.ysk.source.service;

import java.util.List;
import java.util.Map;

import com.ysk.source.entity.DrugInformation;

/**
 * 药品信息
 * 
 * @author admin
 *
 */
public interface DrugInformationSrv {

	/**
	 * 添加药品信息
	 */
	int insert(DrugInformation record);

	/**
	 * 根据主键id查询药品信息
	 */
	DrugInformation selectByPrimaryKey(String drugId);

	/**
	 * 查询药店所有药品信息列表
	 */
	List<DrugInformation> selectByPharmacyId(String pharmacyId);

	/**
	 * 根据条件搜索药品信息
	 */
	List<DrugInformation> selectByPage(Map<String, Object> maps);

	/**
	 * 用户根据条件搜索药品列表
	 */
	List<DrugInformation> selectBysearch(Map<String, Object> maps);

	/**
	 * 查询所有药品信息列表
	 */
	List<DrugInformation> selectAll();

	/**
	 * 修改药品信息记录
	 */
	int updateByPrimaryKey(DrugInformation record);

	/**
	 * 删除药品信息记录（同时删除对应药品详情记录）
	 */
	int deleteByPrimaryKey(String drugId);
}
