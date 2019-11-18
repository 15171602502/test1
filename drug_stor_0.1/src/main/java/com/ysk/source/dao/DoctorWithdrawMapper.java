package com.ysk.source.dao;

import com.ysk.source.entity.DoctorWithdraw;
import java.util.List;
import java.util.Map;

/**
 * 医生提现记录--dao
 * 
 * @author admin
 *
 */
public interface DoctorWithdrawMapper {

	// 添加医生提现记录
	int insert(DoctorWithdraw record);

	// 根据主键id查询提现记录
	DoctorWithdraw selectByPrimaryKey(String recordId);

	// 根据条件查询医生提现记录列表
	List<DoctorWithdraw> selectAllByPage(Map<String, Object> maps);

	// 查询所有医生提现记录
	List<DoctorWithdraw> selectAll();

	// 修改医生提现记录
	int updateByPrimaryKey(DoctorWithdraw record);

	// 删除医生提现记录
	int deleteByPrimaryKey(String recordId);
}