package com.dayuanit.shoppingMall.dto;

import com.dayuanit.shoppingMall.daomain.Merch;

public class BuyMerchDetail {
	private Merch merch;
	private int quantity;

	public Merch getMerch() {
		return merch;
	}

	public void setMerch(Merch merch) {
		this.merch = merch;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
