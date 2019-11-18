package com.ysk.source.service;

import com.ysk.source.entity.ValidateCode;

public interface ValidateCodeSrv {

	public int insertOrUpdateValateCode(ValidateCode validateCode) throws Exception;

	public ValidateCode selectByIdOrMobile(String param) throws Exception;

	public int validate(String code, String mobile);
}
