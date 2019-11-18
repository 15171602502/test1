package com.ysk.source.dao;

import com.ysk.source.entity.FansPurchaseRecord;
import java.util.List;
import java.util.Map;

/**
 * 粉丝购买记录
 * 
 * @author admin
 *
 */
public interface FansPurchaseRecordMapper {

	// 添加记录
	int insert(FansPurchaseRecord record);

	// 根据主键查询记录
	FansPurchaseRecord selectByPrimaryKey(String recordId);

	// 根据条件查询记录
	List<FansPurchaseRecord> selectRecordByPage(Map<String, Object> maps);

	// 查询所有医生的所有粉丝消费记录
	List<FansPurchaseRecord> selectRecordByDoctorId(Map<String, Object> maps);

	// 查询所有记录
	List<FansPurchaseRecord> selectAll();

	// 修改购买记录
	int updateByPrimaryKey(FansPurchaseRecord record);

	// 删除购买记录
	int deleteByPrimaryKey(String recordId);

}