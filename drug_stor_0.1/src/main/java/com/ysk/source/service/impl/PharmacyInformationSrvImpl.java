package com.ysk.source.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.PharmacyInformationMapper;
import com.ysk.source.entity.PharmacyInformation;
import com.ysk.source.service.PharmacyInformationSrv;

@Service
public class PharmacyInformationSrvImpl implements PharmacyInformationSrv {

	@Resource
	private PharmacyInformationMapper pharmacyInformationMapper;

	@Override
	public PharmacyInformation selectByPrimaryKey(String pharmacyId) {
		return pharmacyInformationMapper.selectByPrimaryKey(pharmacyId);
	}

	@Override
	public List<PharmacyInformation> selectAll() {
		return pharmacyInformationMapper.selectAll();
	}

	@Override
	public List<PharmacyInformation> selectPharmacyByPage(PharmacyInformation record) {
		return pharmacyInformationMapper.selectPharmacyByPage(record);
	}

	@Override
	public List<PharmacyInformation> selectPharmacyByLocation(Map<String, Object> maps) {
		return pharmacyInformationMapper.selectPharmacyByLocation(maps);
	}

	@Override
	public List<PharmacyInformation> selectPharmacyByCityCode(String code) {
		return pharmacyInformationMapper.selectPharmacyByCityCode(code);
	}

	@Override
	public int insert(PharmacyInformation record) {
		record.setPharmacyId(UUIDPK.getUUID(this));
		record.setAddTime(StrUtils.dateFormate(new Date()));
		return pharmacyInformationMapper.insert(record);
	}

	@Override
	public int updateByPrimaryKey(PharmacyInformation record) {
		return pharmacyInformationMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String pharmacyId) {
		return pharmacyInformationMapper.deleteByPrimaryKey(pharmacyId);
	}

}
