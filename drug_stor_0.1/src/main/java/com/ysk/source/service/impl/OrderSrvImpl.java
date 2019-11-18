package com.ysk.source.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.source.dao.OrderMapper;
import com.ysk.source.entity.Order;
import com.ysk.source.service.OrderSrv;

@Service
public class OrderSrvImpl implements OrderSrv {

	@Resource
	private OrderMapper orderMapper;

	@Override
	public int insert(Order record) {
		record.setSubmitTime(StrUtils.dateFormate(new Date()));
		return orderMapper.insert(record);
	}

	@Override
	public Order selectByPrimaryKey(String orderId) {
		return orderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public List<Order> selectOrderByPage(Map<String, Object> maps) {
		return orderMapper.selectOrderByPage(maps);
	}

	@Override
	public List<Order> selectAll() {
		return orderMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Order record) {
		return orderMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String orderId) {
		return orderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public int userDeleteOrderById(String orderId) {
		return orderMapper.userDeleteOrderById(orderId);
	}

}
