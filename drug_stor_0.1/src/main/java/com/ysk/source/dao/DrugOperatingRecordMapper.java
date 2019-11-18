package com.ysk.source.dao;

import com.ysk.source.entity.DrugOperatingRecord;
import java.util.List;

/**
 * 药品操作记录--（未实现）
 * 
 * @author admin
 *
 */
public interface DrugOperatingRecordMapper {

	int insert(DrugOperatingRecord record);

	DrugOperatingRecord selectByPrimaryKey(String drugOperatingId);

	List<DrugOperatingRecord> selectAll();

	int updateByPrimaryKey(DrugOperatingRecord record);

	int deleteByPrimaryKey(String drugOperatingId);
}