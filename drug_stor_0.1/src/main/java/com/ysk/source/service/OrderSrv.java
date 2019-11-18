package com.ysk.source.service;

import java.util.List;
import java.util.Map;

import com.ysk.source.entity.Order;
/**
 * 订单
 * @author admin
 *
 */
public interface OrderSrv {

	/**
	 * 添加订单
	 */
	int insert(Order record);

	/**
	 * 根据主键查询订单详情
	 */
	Order selectByPrimaryKey(String orderId);

	/**
	 * 根据条件查询订单列表
	 */
	List<Order> selectOrderByPage(Map<String, Object> maps);

	/**
	 * 查询所有的订单
	 */
	List<Order> selectAll();

	/**
	 * 修改订单信息
	 */
	int updateByPrimaryKey(Order record);

	/**
	 * 删除订单
	 */
	int deleteByPrimaryKey(String orderId);

	/**
	 * 用户删除订单
	 */
	int userDeleteOrderById(String orderId);
}
