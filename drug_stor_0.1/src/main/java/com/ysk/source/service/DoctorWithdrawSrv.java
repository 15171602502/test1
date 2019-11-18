package com.ysk.source.service;

import java.util.List;
import java.util.Map;

import com.ysk.source.entity.DoctorWithdraw;

/**
 * 医生提现记录
 * 
 * @author admin
 *
 */
public interface DoctorWithdrawSrv {

	/**
	 * 添加医生提现记录
	 */
	int insert(DoctorWithdraw record);

	/**
	 * 根据主键id查询提现记录
	 */
	DoctorWithdraw selectByPrimaryKey(String recordId);

	/**
	 * 根据条件查询医生提现记录列表
	 */
	List<DoctorWithdraw> selectAllByPage(Map<String, Object> maps);

	/**
	 * 查询所有医生提现记录
	 */
	List<DoctorWithdraw> selectAll();

	/**
	 * 修改医生提现记录
	 */
	int updateByPrimaryKey(DoctorWithdraw record);

	/**
	 * 删除医生提现记录
	 */
	int deleteByPrimaryKey(String recordId);
}
