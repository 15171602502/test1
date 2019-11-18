package com.ysk.source.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.DoctorWithdrawMapper;
import com.ysk.source.entity.DoctorWithdraw;
import com.ysk.source.service.DoctorWithdrawSrv;

@Service
public class DoctorWithdrawSrvImpl implements DoctorWithdrawSrv {

	@Resource
	private DoctorWithdrawMapper DoctorWithdrawMapper;

	@Override
	public int insert(DoctorWithdraw record) {
		record.setRecordId(UUIDPK.getUUID(this));
		record.setWithdrawalTime(StrUtils.dateFormate(new Date()));
		return DoctorWithdrawMapper.insert(record);
	}

	@Override
	public DoctorWithdraw selectByPrimaryKey(String recordId) {
		return DoctorWithdrawMapper.selectByPrimaryKey(recordId);
	}

	@Override
	public List<DoctorWithdraw> selectAllByPage(Map<String, Object> maps) {
		return DoctorWithdrawMapper.selectAllByPage(maps);
	}

	@Override
	public List<DoctorWithdraw> selectAll() {
		return DoctorWithdrawMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(DoctorWithdraw record) {
		return DoctorWithdrawMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String recordId) {
		return DoctorWithdrawMapper.deleteByPrimaryKey(recordId);
	}

}
