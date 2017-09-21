package com.dayuanit.shoppingMall.eunms;

import com.dayuanit.shoppingMall.exception.ShoppingMallException;

public enum RegistStatusEnum {
	USABLE(1, "可用"), UN_USABLE(2, "不可用");

	private int k;
	private String v;

	public int getK() {
		return k;
	}

	public String getV() {
		return v;
	}
	
	private RegistStatusEnum(int k, String v) {
		this.k = k;
		this.v = v;
	}
	
	public static RegistStatusEnum getEnum(int k) {
		for (RegistStatusEnum rse : RegistStatusEnum.values()) {
			if (k == rse.getK()) {
				return rse;
			}
		}
		throw new ShoppingMallException("没有合适的状态");
	}
}
