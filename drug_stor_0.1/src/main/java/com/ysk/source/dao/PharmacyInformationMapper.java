package com.ysk.source.dao;

import com.ysk.source.entity.PharmacyInformation;
import java.util.List;
import java.util.Map;

public interface PharmacyInformationMapper {

	// 根据药店ID获取药店信息（app用户端）
	PharmacyInformation selectByPrimaryKey(String pharmacyId);

	// 获取所有药店信息（app用户端）
	List<PharmacyInformation> selectAll();

	// 根据条件获取药店信息列表（app用户端）
	List<PharmacyInformation> selectPharmacyByPage(PharmacyInformation record);

	// 根据定位获取药店信息列表（app用户端）
	List<PharmacyInformation> selectPharmacyByLocation(Map<String, Object> maps);

	// 根据城市code获取药店信息列表（app用户端）
	List<PharmacyInformation> selectPharmacyByCityCode(String code);

	// 添加药店信息（药店端）
	int insert(PharmacyInformation record);

	// 修改药店信息（药店端）
	int updateByPrimaryKey(PharmacyInformation record);

	// 删除药店信息
	int deleteByPrimaryKey(String pharmacyId);
}