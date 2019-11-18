package com.ysk.source.dao;

import com.ysk.source.entity.UserInfo;
import java.util.List;

public interface UserInfoMapper {

	// 添加用户
	int insertUser(UserInfo record);

	// 根据userId查询用户信息
	UserInfo selectByPrimaryKey(String userId);

	// 用户登录
	UserInfo userLoginByPhone(String phone);

	// 验证医生邀请码是否存在
	List<UserInfo> selectInvitationCode(String invitationCode);

	// 查询所有的用户信息
	List<UserInfo> selectFansByDoctorId(String userId);

	// 查询所有的用户信息
	List<UserInfo> selectAll();

	// 根据userId修改用户信息
	int updateByPrimaryKey(UserInfo record);

	// 删除用户信息
	int deleteByPrimaryKey(String userId);
}