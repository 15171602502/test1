package com.ysk.source.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.UserInfoMapper;
import com.ysk.source.entity.UserInfo;
import com.ysk.source.service.UserInfoSrv;

@Service
public class UserInfoSrvImpl implements UserInfoSrv {

	@Resource
	private UserInfoMapper userInfoMapper;

	@Override
	public int insertUser(UserInfo record) {
		record.setUserId(UUIDPK.getUUID(this));
		record.setAddTime(StrUtils.dateFormate(new Date()));
		return userInfoMapper.insertUser(record);
	}

	@Override
	public UserInfo selectByPrimaryKey(String userId) {
		return userInfoMapper.selectByPrimaryKey(userId);
	}

	@Override
	public UserInfo userLoginByPhone(String phone) {
		return userInfoMapper.userLoginByPhone(phone);
	}

	@Override
	public List<UserInfo> selectInvitationCode(String invitationCode) {
		return userInfoMapper.selectInvitationCode(invitationCode);
	}

	@Override
	public List<UserInfo> selectFansByDoctorId(String userId) {
		return userInfoMapper.selectFansByDoctorId(userId);
	}

	@Override
	public List<UserInfo> selectAll() {
		return userInfoMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(UserInfo record) {
		return userInfoMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String userId) {
		return userInfoMapper.deleteByPrimaryKey(userId);
	}

}
