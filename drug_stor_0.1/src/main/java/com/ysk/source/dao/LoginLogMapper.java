package com.ysk.source.dao;

import com.ysk.source.entity.LoginLog;
import java.util.List;
import java.util.Map;

/**
 * 登录日志
 * 
 * @author admin
 *
 */
public interface LoginLogMapper {

	// 添加记录
	int insert(LoginLog record);

	// 根据之间查询记录
	LoginLog selectByPrimaryKey(String logId);

	// 根据条件查询相关记录
	List<LoginLog> selectRdcordByPage(Map<String, Object> maps);

	// 查询所有
	List<LoginLog> selectAll();

	// 修改记录
	int updateByPrimaryKey(LoginLog record);

	// 删除记录
	int deleteByPrimaryKey(String logId);
}