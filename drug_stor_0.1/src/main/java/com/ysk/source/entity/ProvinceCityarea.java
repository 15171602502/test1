package com.ysk.source.entity;

/**
 * 省市区资源表--实体类
 * 
 * @author admin
 *
 */
public class ProvinceCityarea {

	private String id;

	private String code;

	private String name;

	private String parentCode;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
}