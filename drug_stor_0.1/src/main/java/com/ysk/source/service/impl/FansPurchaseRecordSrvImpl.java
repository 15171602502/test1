package com.ysk.source.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.source.dao.FansPurchaseRecordMapper;
import com.ysk.source.entity.FansPurchaseRecord;
import com.ysk.source.service.FansPurchaseRecordSrv;

@Service
public class FansPurchaseRecordSrvImpl implements FansPurchaseRecordSrv {

	@Resource
	private FansPurchaseRecordMapper fansPurchaseRecordMapper;

	@Override
	public int insert(FansPurchaseRecord record) {
		return fansPurchaseRecordMapper.insert(record);
	}

	@Override
	public FansPurchaseRecord selectByPrimaryKey(String recordId) {
		return fansPurchaseRecordMapper.selectByPrimaryKey(recordId);
	}

	@Override
	public List<FansPurchaseRecord> selectRecordByPage(Map<String, Object> maps) {
		return fansPurchaseRecordMapper.selectRecordByPage(maps);
	}

	@Override
	public List<FansPurchaseRecord> selectRecordByDoctorId(Map<String, Object> maps) {
		return fansPurchaseRecordMapper.selectRecordByDoctorId(maps);
	}

	@Override
	public List<FansPurchaseRecord> selectAll() {
		return fansPurchaseRecordMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(FansPurchaseRecord record) {
		return fansPurchaseRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String recordId) {
		return fansPurchaseRecordMapper.deleteByPrimaryKey(recordId);
	}

}
