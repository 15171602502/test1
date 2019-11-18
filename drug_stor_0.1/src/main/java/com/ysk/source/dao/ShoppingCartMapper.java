package com.ysk.source.dao;

import com.ysk.source.entity.ShoppingCart;
import java.util.List;
import java.util.Map;

/**
 * 购物车--dao
 * 
 * @author admin
 *
 */
public interface ShoppingCartMapper {

	// 添加购物车
	int insert(ShoppingCart record);

	// 查询购物车
	ShoppingCart selectByPrimaryKey(String shoppingCartId);

	// 根据指定条件查询购物车
	List<ShoppingCart> selectCartByUserId(Map<String, Object> maps);

	// 查询所有购物车
	List<ShoppingCart> selectAll();

	// 修改购物车
	int updateByPrimaryKey(ShoppingCart record);

	// 删除购物车
	int deleteByPrimaryKey(String shoppingCartId);

	// 提交订单，删除用户购物车
	int deleteByUserOrder(Map<String, Object> maps);
}