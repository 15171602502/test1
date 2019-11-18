package com.ysk.source.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.CollectRecordsMapper;
import com.ysk.source.entity.CollectRecords;
import com.ysk.source.service.CollectRecordsSrv;

@Service
public class CollectRecordsSrvImpl implements CollectRecordsSrv {

	@Resource
	private CollectRecordsMapper collectRecordsMapper;

	@Override
	public int insert(CollectRecords record) {
		record.setCollectId(UUIDPK.getUUID(this));
		record.setAddTime(StrUtils.dateFormate(new Date()));
		return collectRecordsMapper.insert(record);
	}

	@Override
	public int deleteCollectPharmacy(Map<String, Object> maps) {
		return collectRecordsMapper.deleteCollectPharmacy(maps);
	}

	@Override
	public CollectRecords selectByPrimaryKey(String collectId) {
		return collectRecordsMapper.selectByPrimaryKey(collectId);
	}

	@Override
	public CollectRecords selectWhetherCollectByuserId(String userId, Integer collectType, String productId) {
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("userId", userId);
		map0.put("collectType", collectType);
		map0.put("productId", productId);
		return collectRecordsMapper.selectWhetherCollectByuserId(map0);
	}

	@Override
	public List<CollectRecords> getCollectListByUserId(Map<String, Object> maps) {
		return collectRecordsMapper.getCollectListByUserId(maps);
	}

	@Override
	public List<CollectRecords> selectAll() {
		return collectRecordsMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(CollectRecords record) {
		return collectRecordsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String collectId) {
		return collectRecordsMapper.deleteByPrimaryKey(collectId);
	}
}
