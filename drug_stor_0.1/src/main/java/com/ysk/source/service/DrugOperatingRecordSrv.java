package com.ysk.source.service;

import java.util.List;

import com.ysk.source.entity.DrugOperatingRecord;

/**
 * 药品操作记录--（未实现）
 * 
 * @author admin
 *
 */
public interface DrugOperatingRecordSrv {

	int insert(DrugOperatingRecord record);

	DrugOperatingRecord selectByPrimaryKey(String drugOperatingId);

	List<DrugOperatingRecord> selectAll();

	int updateByPrimaryKey(DrugOperatingRecord record);

	int deleteByPrimaryKey(String drugOperatingId);
}
