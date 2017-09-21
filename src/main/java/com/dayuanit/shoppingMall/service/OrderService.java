package com.dayuanit.shoppingMall.service;

import java.util.List;
import java.util.Map;

import com.dayuanit.shoppingMall.daomain.Order;
import com.dayuanit.shoppingMall.dto.BuyMerchDetail;
import com.dayuanit.shoppingMall.dto.MerchOrderInfoDTO;
import com.dayuanit.shoppingMall.dto.MyOrderDTO;
import com.dayuanit.shoppingMall.utils.PageUtils;
import com.dayuanit.shoppingMall.vo.BuyMerchVO;

public interface OrderService {
	
	Order createOrderFormShopCar(List<BuyMerchVO> vos, int registId);
	
	List<BuyMerchDetail> createOrderFormShopCar(String buyMsg, int registId);
	
	MerchOrderInfoDTO loadOrderInfo(int orderId, int registId);
	
	Map<String, Object> pay(int orderId, int payChannle, int addressId, int regist);
	
	PageUtils<MyOrderDTO> listEffectivedOrder(int page, Integer registId, Integer status);
}
