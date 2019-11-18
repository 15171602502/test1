package com.ysk.source.dao;

import com.ysk.source.entity.DrugDetails;
import java.util.List;

/**
 * 药品详情--dao
 * 
 * @author admin
 *
 */
public interface DrugDetailsMapper {

	// 添加药品详情
	int insert(DrugDetails record);

	// 根据主键查询药品详情
	DrugDetails selectByPrimaryKey(String drugId);

	// 查询所有详情
	List<DrugDetails> selectAll();

	// 根据主键修改药品详情
	int updateByPrimaryKey(DrugDetails record);

	// 删除药品详情
	int deleteByPrimaryKey(String drugId);
}