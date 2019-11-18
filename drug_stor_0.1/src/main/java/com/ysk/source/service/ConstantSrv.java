package com.ysk.source.service;

import java.util.List;

import com.ysk.source.entity.Constant;

/**
 * 常量 （未实现）
 * 
 * @author admin
 *
 */
public interface ConstantSrv {

	int deleteByPrimaryKey(String constantId);

	int insert(Constant record);

	Constant selectByPrimaryKey(String constantId);

	List<Constant> selectAll();

	int updateByPrimaryKey(Constant record);
}
