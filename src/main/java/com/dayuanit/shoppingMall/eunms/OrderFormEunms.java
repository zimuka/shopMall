package com.dayuanit.shoppingMall.eunms;

public enum OrderFormEunms {
	NOW_PAY(1, "直接支付"), CART(2, "购物车结算");
	
	private int k;
	private String v;
	
	private OrderFormEunms (int k, String v) {
		this.k = k;
		this.v = v;
	}

	public int getK() {
		return k;
	}

	public String getV() {
		return v;
	}
	
}
