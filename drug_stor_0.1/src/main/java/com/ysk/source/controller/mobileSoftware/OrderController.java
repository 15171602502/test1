package com.ysk.source.controller.mobileSoftware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ysk.kxt.sourceUit.ResultUit;
import com.ysk.kxt.util.string.StrUtils;
import com.ysk.kxt.util.uuid.UUIDPK;
import com.ysk.source.entity.DrugDetails;
import com.ysk.source.entity.DrugInformation;
import com.ysk.source.entity.Order;
import com.ysk.source.entity.OrderDetails;
import com.ysk.source.entity.PharmacyInformation;
import com.ysk.source.service.DrugDetailsSrv;
import com.ysk.source.service.DrugInformationSrv;
import com.ysk.source.service.OrderDetailsSrv;
import com.ysk.source.service.OrderSrv;
import com.ysk.source.service.PharmacyInformationSrv;
import com.ysk.source.service.ShoppingCartSrv;

/**
 * 订单相关接口
 * 
 * @author admin
 *
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Resource
	private OrderSrv OrderSrv;
	@Resource
	private OrderDetailsSrv orderDetailsSrv;
	@Resource
	private DrugInformationSrv drugInformationSrv;
	@Resource
	private DrugDetailsSrv drugDetailsSrv;
	@Resource
	private ShoppingCartSrv shoppingCartSrv;
	@Resource
	private PharmacyInformationSrv pharmacyInformationSrv;

	/**
	 * 用户添加订单
	 * 
	 * @param userId
	 *            用户id
	 * @param prescription
	 *            购药处方
	 * @param commodity
	 *            商品集合
	 * @return
	 */
	@RequestMapping("/addOrder")
	@ResponseBody
	@Transactional
	public Object addOrder(@RequestParam(required = true) String userId,
			@RequestParam(required = true) String prescription,
			@RequestParam("commodity") @RequestBody String commodity) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		if (StrUtils.isEmpty(commodity)) {
			return new ResultUit("10010", "请选择购买药品");
		}
		String orderId = UUIDPK.get16BitsId();// 订单id
		int totalMoney = 0;// 订单总金额
		List<String> drugIdList = new ArrayList<String>();

		JSONArray js = JSONObject.parseArray(commodity);
		boolean flag = false;
		for (Object sss : js) {
			Map<String, Object> map0 = (Map<String, Object>) sss;
			String drugId = map0.get("drugId").toString();// 药品id

			DrugInformation din = drugInformationSrv.selectByPrimaryKey(drugId);// 查询药品详情
			if (!StrUtils.isEmpty(din)) {
				int purchaseQuantity = Integer.parseInt(map0.get("quantity").toString());
				int money = din.getBuyingPrice() * purchaseQuantity;

				OrderDetails ods = new OrderDetails();
				ods.setOrderId(orderId);// 订单id
				ods.setDrugId(drugId);// 药品iD
				ods.setPurchaseQuantity(purchaseQuantity);// 购买数量
				ods.setDrugName(din.getDrugName());// 商品名称
				ods.setBannerImg(din.getBannerImg());// banner图

				DrugDetails dds = drugDetailsSrv.selectByPrimaryKey(drugId);
				if (!StrUtils.isEmpty(dds)) {
					ods.setMajorFunction(dds.getMajorFunction());// 功能主治
					ods.setAnnouncements(dds.getAnnouncements());// 注意事项
				}
				ods.setPharmacyId(din.getPharmacyId());// 药店id
				ods.setTotalMoney(money);// 单项总价
				ods.setOrderState(2);
				// 添加详情记录
				if (orderDetailsSrv.insert(ods) > 0) {
					totalMoney = totalMoney + money;
					drugIdList.add(drugId);
				}
				flag = true;
			} else {
				orderDetailsSrv.deleteByOrderId(orderId);
				return new ResultUit("10010", "预约失败,无法查询药品信息");
			}
		}
		if (flag) {
			Order order = new Order();
			order.setOrderId(orderId);// 订单id
			order.setUserId(userId);// 用户id
			order.setOrderState(2); // 订单状态
			order.setTotalMoney(totalMoney); // 总金额
			order.setPrescription(prescription); // 购药处方
			if (OrderSrv.insert(order) > 0) {
				// 删除购物车记录
				for (String drugIdInfo : drugIdList) {
					Map<String, Object> delMap = new HashMap<String, Object>();
					delMap.put("userId", userId);
					delMap.put("drugId", drugIdInfo);
					shoppingCartSrv.deleteByUserOrder(delMap);
				}
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("orderId", orderId);
				return new ResultUit("10000", "预约成功", map1);
			} else {
				return new ResultUit("10010", "预约失败");
			}
		} else {
			return new ResultUit("10010", "预约失败");
		}
	}

	/**
	 * 用户购物记录
	 * 
	 * @param userId
	 *            用户id
	 * @param orderType
	 *            订单状态 1、待预约；2、已预约；3、已完成；
	 * @return
	 */
	@RequestMapping(value = "/getUserOrderList")
	@ResponseBody
	public Object getUserOrderList(@RequestParam(required = true) String userId,
			@RequestParam(required = true) Integer orderState) {
		// Map<String, Object> resultMap = new HashMap<String, Object>();

		Map<String, Object> orderMap = new HashMap<String, Object>();
		orderMap.put("userId", userId);
		orderMap.put("orderState", orderState);
		orderMap.put("userDlt", 0);
		System.out.println("userId=" + userId + ",=orderState" + orderState);
		List<Order> orderList = OrderSrv.selectOrderByPage(orderMap);
		for (Order order2 : orderList) {
			if (!StrUtils.isEmpty(order2.getOrderId())) {
				Map<String, Object> detailsMap = new HashMap<String, Object>();
				detailsMap.put("orderId", order2.getOrderId());
				List<OrderDetails> drugList = orderDetailsSrv.selectDetailsByPage(detailsMap);
				order2.setDrugList(drugList);
				if (drugList.size() > 0 && !StrUtils.isEmpty(drugList.get(0).getPharmacyId())) {
					PharmacyInformation pharmacyInfo = pharmacyInformationSrv
							.selectByPrimaryKey(drugList.get(0).getPharmacyId());
					order2.setPharmacyInfo(pharmacyInfo);
				}
			}
		}

		Map<String, Object> resultListMap = new HashMap<String, Object>();
		resultListMap.put("orderList", orderList);

		return new ResultUit("10000", "请求成功", resultListMap);
	}

}
