package com.ysk.source.controller.mobileSoftware;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ysk.kxt.sourceUit.ResultUit;
import com.ysk.source.entity.DrugInformation;
import com.ysk.source.entity.ShoppingCart;
import com.ysk.source.service.DrugInformationSrv;
import com.ysk.source.service.ShoppingCartSrv;

/**
 * 购物车相关接口
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping(value = "/shoppingCart")
public class ShoppingCartController {

	@Resource
	private ShoppingCartSrv shoppingCartSrv;
	@Resource
	private DrugInformationSrv drugInformationSrv;

	/**
	 * 添加药品至购物车
	 * 
	 * @param userId
	 *            用户id
	 * @param drugId
	 *            药品id
	 * @param quantity
	 *            添加数量
	 * @return
	 */
	@RequestMapping(value = "/addShoppingCartByUserId")
	@ResponseBody
	public Object addShoppingCartByUserId(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String drugId,
			@RequestParam(value = "quantity", defaultValue = "1") Integer quantity) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUserId(userId);
		shoppingCart.setDrugId(drugId);
		shoppingCart.setQuantity(quantity);
		if (shoppingCartSrv.insert(shoppingCart) > 0) {
			return new ResultUit("10000", "添加成功");
		} else {
			return new ResultUit("10010", "添加失败");
		}
	}

	/**
	 * 修改购物车数量
	 * 
	 * @param shoppingCartId
	 * @param amount
	 * @return
	 */
	@RequestMapping(value = "/updateShoppingCartAmount")
	@ResponseBody
	public Object updateShoppingCartAmount(@RequestParam(required = true) String shoppingCartId,
			@RequestParam(required = true) Integer amount) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setShoppingCartId(shoppingCartId);
		shoppingCart.setQuantity(amount);
		if (shoppingCartSrv.updateByPrimaryKey(shoppingCart) > 0) {
			return new ResultUit("10000", "修改成功");
		} else {
			return new ResultUit("10010", "修改失败");
		}
	}

	/**
	 * 用户查询购物车列表
	 * 
	 * @param userId
	 *            用户id
	 * @return
	 */
	@RequestMapping(value = "/getUserShoppingCart")
	@ResponseBody
	public Object getUserShoppingCart(@RequestParam(required = true) String userId) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> mapInfo = new HashMap<String, Object>();
		mapInfo.put("userId", userId);
		List<ShoppingCart> cartList = shoppingCartSrv.selectCartByUserId(mapInfo);
		Map<String, Object> map1 = new HashMap<String, Object>();
		for (ShoppingCart shoppingCart : cartList) {
			DrugInformation drugInfo = drugInformationSrv.selectByPrimaryKey(shoppingCart.getDrugId());
			shoppingCart.setDrugInfo(drugInfo);
		}

		map1.put("shoppingCartList", cartList);
		return new ResultUit("10000", "请求成功", map1);
	}
	
	@RequestMapping(value="/deleteCatByUser")
	@ResponseBody
	public Object deleteCatByUser(@RequestParam(required = true) String userId,@RequestParam("drugIdList[]") List<String> drugIdList){
		// 删除购物车记录
		for (String drugIdInfo : drugIdList) {
			Map<String, Object> delMap = new HashMap<String, Object>();
			delMap.put("userId", userId);
			delMap.put("drugId", drugIdInfo);
			shoppingCartSrv.deleteByUserOrder(delMap);
		}
		return new ResultUit("10000", "删除成功");
	}
}
