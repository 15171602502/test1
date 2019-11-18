package com.ysk.source.service;

import java.util.List;
import java.util.Map;

import com.ysk.source.entity.OrderDetails;

/**
 * 订单详情
 * 
 * @author admin
 *
 */
public interface OrderDetailsSrv {

	/**
	 * 添加订单详情
	 */
	int insert(OrderDetails record);

	/**
	 * 根据主键查询订单详情
	 */
	OrderDetails selectByPrimaryKey(String detailsId);

	/**
	 * 查询所有订单详情
	 */
	List<OrderDetails> selectDetailsByPage(Map<String, Object> maps);

	/**
	 * 查询所有订单详情
	 */
	List<OrderDetails> selectAll();

	/**
	 * 修改订单详情
	 */
	int updateByPrimaryKey(OrderDetails record);

	/**
	 * 根据订单id修改订单状态
	 */
	int updateByOrderId(Map<String, Object> maps);

	/**
	 * 删除订单详情
	 */
	int deleteByPrimaryKey(String detailsId);

	/**
	 * 根据订单id，删除订单详情
	 */
	int deleteByOrderId(String orderId);
}
