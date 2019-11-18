package com.ysk.source.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.LoginLogMapper;
import com.ysk.source.entity.LoginLog;
import com.ysk.source.service.LoginLogSrv;

@Service
public class LoginLogSrvImpl implements LoginLogSrv {
	@Resource
	private LoginLogMapper loginLogMapper;

	@Override
	public int insert(LoginLog record) {
		record.setLogId(UUIDPK.getUUID(this));
		record.setLoginTime(StrUtils.dateFormate(new Date()));
		return loginLogMapper.insert(record);
	}

	@Override
	public LoginLog selectByPrimaryKey(String logId) {
		return loginLogMapper.selectByPrimaryKey(logId);
	}

	@Override
	public List<LoginLog> selectRdcordByPage(Map<String, Object> maps) {
		return loginLogMapper.selectRdcordByPage(maps);
	}

	@Override
	public List<LoginLog> selectAll() {
		return loginLogMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(LoginLog record) {
		return loginLogMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String logId) {
		return loginLogMapper.deleteByPrimaryKey(logId);
	}

}
