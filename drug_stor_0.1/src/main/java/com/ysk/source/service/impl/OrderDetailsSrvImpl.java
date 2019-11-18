package com.ysk.source.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.OrderDetailsMapper;
import com.ysk.source.entity.OrderDetails;
import com.ysk.source.service.OrderDetailsSrv;

@Service
public class OrderDetailsSrvImpl implements OrderDetailsSrv {

	@Resource
	private OrderDetailsMapper OrderDetailsMapper;

	@Override
	public int insert(OrderDetails record) {
		record.setDetailsId(UUIDPK.getUUID(this));
		return OrderDetailsMapper.insert(record);
	}

	@Override
	public OrderDetails selectByPrimaryKey(String detailsId) {
		return OrderDetailsMapper.selectByPrimaryKey(detailsId);
	}

	@Override
	public List<OrderDetails> selectDetailsByPage(Map<String, Object> maps) {
		return OrderDetailsMapper.selectDetailsByPage(maps);
	}

	@Override
	public List<OrderDetails> selectAll() {
		return OrderDetailsMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(OrderDetails record) {
		return OrderDetailsMapper.updateByPrimaryKey(record);
	}

	@Override
	public int updateByOrderId(Map<String, Object> maps) {
		return OrderDetailsMapper.updateByOrderId(maps);
	}

	@Override
	public int deleteByPrimaryKey(String detailsId) {
		return OrderDetailsMapper.deleteByPrimaryKey(detailsId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return OrderDetailsMapper.deleteByOrderId(orderId);
	}

}
