package com.ysk.source.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.source.dao.DrugDetailsMapper;
import com.ysk.source.entity.DrugDetails;
import com.ysk.source.service.DrugDetailsSrv;

@Service
public class DrugDetailsSrvImpl implements DrugDetailsSrv {

	@Resource
	private DrugDetailsMapper drugDetailsMapper;

	@Override
	public int insert(DrugDetails record) {
		return drugDetailsMapper.insert(record);
	}

	@Override
	public DrugDetails selectByPrimaryKey(String drugId) {
		return drugDetailsMapper.selectByPrimaryKey(drugId);
	}

	@Override
	public List<DrugDetails> selectAll() {
		return drugDetailsMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(DrugDetails record) {
		return drugDetailsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String drugId) {
		return drugDetailsMapper.deleteByPrimaryKey(drugId);
	}

}
