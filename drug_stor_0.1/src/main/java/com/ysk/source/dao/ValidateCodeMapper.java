package com.ysk.source.dao;

import com.ysk.source.entity.ValidateCode;

public interface ValidateCodeMapper {

	public int insert(ValidateCode validateCode);

	public ValidateCode selectByIdOrMobile(String param);

	public int update(ValidateCode validateCode);
}