package com.dayuanit.shoppingMall.eunms;

public enum MerchStautsEnums {
	UP(1, "上架"), DOWN(2, "已下架");
	
	private int k;
	private String v;

	private MerchStautsEnums(int k, String v) {
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
