package com.ysk.source.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ysk.source.entity.DrugOperatingRecord;
import com.ysk.source.service.DrugOperatingRecordSrv;

@Service
public class DrugOperatingRecordSrvImpl implements DrugOperatingRecordSrv {

	@Override
	public int insert(DrugOperatingRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public DrugOperatingRecord selectByPrimaryKey(String drugOperatingId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DrugOperatingRecord> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKey(DrugOperatingRecord record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByPrimaryKey(String drugOperatingId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
