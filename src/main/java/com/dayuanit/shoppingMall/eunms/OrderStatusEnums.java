package com.dayuanit.shoppingMall.eunms;

import com.dayuanit.shoppingMall.exception.ShoppingMallException;

public enum OrderStatusEnums {
	
	待支付(0, "待支付"), 待结算(1, "待结算"), 已结算(2, "已支付"), 取消订单(3, "取消订单"), 订单超时(4, "订单超时"); 
	
	private int k;
	private String v;
	
	private OrderStatusEnums(int k, String v) {
		this.k = k;
		this.v = v;
	}
	
	public static OrderStatusEnums getOrderStatus(int k) {
		for (OrderStatusEnums ose : OrderStatusEnums.values()) {
			if (k == ose.getK()) {
				return ose;
			}
		}
		
		throw new ShoppingMallException("没有合适的状态");
	}
	
	public int getK() {
		return k;
	}

	public String getV() {
		return v;
	}
	
}
