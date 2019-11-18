package com.ysk.source.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.ProvinceCityareaMapper;
import com.ysk.source.entity.ProvinceCityarea;
import com.ysk.source.service.ProvinceCityareaSrv;

@Service
public class ProvinceCityareaSrvImpl implements ProvinceCityareaSrv {

	@Resource
	private ProvinceCityareaMapper provinceCityareaMapper;

	@Override
	public int insert(ProvinceCityarea record) {
		record.setId(UUIDPK.getUUID(this));
		return provinceCityareaMapper.insert(record);
	}

	@Override
	public List<ProvinceCityarea> selectAll() {
		return provinceCityareaMapper.selectAll();
	}

	@Override
	public List<ProvinceCityarea> selectProvincecityareaByPage(Map<String, Object> maps) {
		return provinceCityareaMapper.selectProvincecityareaByPage(maps);
	}

	@Override
	public List<ProvinceCityarea> selectProvincecityareaBySearch(String searchCriteria) {
		return provinceCityareaMapper.selectProvincecityareaBySearch(searchCriteria);
	}

	@Override
	public ProvinceCityarea selectProvincecityareaById(ProvinceCityarea provincecityarea) {
		return provinceCityareaMapper.selectProvincecityareaById(provincecityarea);
	}

	@Override
	public ProvinceCityarea selectProvincecityareaByCode(String code) {
		return provinceCityareaMapper.selectProvincecityareaByCode(code);
	}

	@Override
	public int updateProvincecityareaById(ProvinceCityarea provincecityarea) {
		return provinceCityareaMapper.updateProvincecityareaById(provincecityarea);
	}

	@Override
	public int deleteProvincecityareaById(String id) {
		return provinceCityareaMapper.deleteProvincecityareaById(id);
	}

}
