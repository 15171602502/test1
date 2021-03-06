package com.ysk.source.service;

import java.util.List;
import java.util.Map;

import com.ysk.source.entity.ShoppingCart;

/**
 * 购物车
 * 
 * @author admin
 *
 */
public interface ShoppingCartSrv {

	/**
	 * 添加购物车
	 */
	int insert(ShoppingCart record);

	/**
	 * 查询购物车
	 */
	ShoppingCart selectByPrimaryKey(String shoppingCartId);

	/**
	 * 根据指定条件查询购物车
	 */
	List<ShoppingCart> selectCartByUserId(Map<String, Object> maps);

	/**
	 * 查询所有购物车
	 */
	List<ShoppingCart> selectAll();

	/**
	 * 修改购物车
	 */
	int updateByPrimaryKey(ShoppingCart record);

	/**
	 * 删除购物车
	 */
	int deleteByPrimaryKey(String shoppingCartId);

	/**
	 * 提交订单，删除用户购物车
	 */
	int deleteByUserOrder(Map<String, Object> maps);

}
