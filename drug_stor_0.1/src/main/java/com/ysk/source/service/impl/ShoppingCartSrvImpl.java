package com.ysk.source.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ysk.kxt.util.string.StrUtils;
import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.dao.ShoppingCartMapper;
import com.ysk.source.entity.ShoppingCart;
import com.ysk.source.service.ShoppingCartSrv;

@Service
public class ShoppingCartSrvImpl implements ShoppingCartSrv {

	@Resource
	private ShoppingCartMapper shoppingCartMapper;

	@Override
	public int insert(ShoppingCart record) {
		record.setShoppingCartId(UUIDPK.getUUID(this));
		record.setAddTime(StrUtils.dateFormate(new Date()));
		return shoppingCartMapper.insert(record);
	}

	@Override
	public ShoppingCart selectByPrimaryKey(String shoppingCartId) {
		return shoppingCartMapper.selectByPrimaryKey(shoppingCartId);
	}

	@Override
	public List<ShoppingCart> selectCartByUserId(Map<String, Object> maps) {
		return shoppingCartMapper.selectCartByUserId(maps);
	}

	@Override
	public List<ShoppingCart> selectAll() {
		return shoppingCartMapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(ShoppingCart record) {
		return shoppingCartMapper.updateByPrimaryKey(record);
	}

	@Override
	public int deleteByPrimaryKey(String shoppingCartId) {
		return shoppingCartMapper.deleteByPrimaryKey(shoppingCartId);
	}

	@Override
	public int deleteByUserOrder(Map<String, Object> maps) {
		return shoppingCartMapper.deleteByUserOrder(maps);
	}

}
