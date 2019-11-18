package com.ysk.source.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.ValidateCodeMapper;
import com.ysk.source.entity.ValidateCode;
import com.ysk.source.service.ValidateCodeSrv;

@Service
public class ValidateCodeSrvImpl implements ValidateCodeSrv {

	@Autowired
	private ValidateCodeMapper validateCodeMapper;

	private final static int EXPIRE_MIN = 2;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public int insertOrUpdateValateCode(ValidateCode validateCode) throws Exception {
		ValidateCode code = selectByIdOrMobile(validateCode.getMobileNo());
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, EXPIRE_MIN);

		// 查询到该手机号已有记录
		if (code != null && code.getId() != null) {
			// 更新该手机号记录
			code.setExpireTime(sdf.format(cal.getTime()).toString());
			code.setCode(validateCode.getCode());

			return validateCodeMapper.update(code);
		} else {
			// 生成新纪录
			validateCode.setId(UUIDPK.getUUID(this));
			validateCode.setExpireTime(sdf.format(cal.getTime()).toString());
			return validateCodeMapper.insert(validateCode);
		}
	}

	@Override
	public ValidateCode selectByIdOrMobile(String param) throws Exception {
		return validateCodeMapper.selectByIdOrMobile(param);
	}

	/**
	 * 0验证失败 1验证成功 2过期
	 */
	@Override
	public int validate(String code, String mobile) {
		try {
			ValidateCode validateCode = selectByIdOrMobile(mobile);
			if (validateCode != null) {
				Date dateNow = new Date();
				// 如果 当前时间在过期时间之后
				if (dateNow.after(sdf.parse(validateCode.getExpireTime()))) {
					return 2;
				} else {
					// 在有效期内
					if (code.equals(validateCode.getCode())) {
						return 1;
					} else {
						return 0;
					}
				}
			}
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
