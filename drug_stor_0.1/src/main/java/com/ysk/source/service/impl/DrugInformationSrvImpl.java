package com.ysk.source.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.DrugInformationMapper;
import com.ysk.source.entity.DrugInformation;
import com.ysk.source.service.DrugInformationSrv;

@Service
public class DrugInformationSrvImpl implements DrugInformationSrv {

	@Resource
	private DrugInformationMapper drugInformationMapper;

	@Override
	public int insert(DrugInformation record) {
		record.setDrugId(UUIDPK.getUUID(this));
		record.setAddTime(StrUtils.dateFormate(new Date()));
		return drugInformationMapper.insert(record);
	}

	@Override
	public DrugInformation selectByPrimaryKey(String drugId) {
		return drugInformationMapper.selectByPrimaryKey(drugId);
	}

	@Override
	public List<DrugInformation> selectByPharmacyId(String pharmacyId) {
		return drugInformationMapper.selectByPharmacyId(pharmacyId);
	}

	@Override
	public List<DrugInformation> selectByPage(Map<String, Object> maps) {
		return drugInformationMapper.selectByPage(maps);
	}

	@Override
	public List<DrugInformation> selectBysearch(Map<String, Object> maps) {
		return drugInformationMapper.selectBysearch(maps);
	}

	@Override
	public List<DrugInformation> selectAll() {
		return drugInformationMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(DrugInformation record) {
		return drugInformationMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String drugId) {
		return drugInformationMapper.deleteByPrimaryKey(drugId);
	}

}
