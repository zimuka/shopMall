package com.dayuanit.shoppingMall.dto;

import java.util.List;

public class MerchOrderInfoDTO {
	private String amount;
	private String freight;
	private String preferentialAmount;
	private List<MerchInfo> merchs;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getPreferentialAmount() {
		return preferentialAmount;
	}

	public void setPreferentialAmount(String preferentialAmount) {
		this.preferentialAmount = preferentialAmount;
	}

	public List<MerchInfo> getMerchs() {
		return merchs;
	}

	public void setMerchs(List<MerchInfo> merchs) {
		this.merchs = merchs;
	}

	public static class MerchInfo {
		private String merchName;
		private String amount;
		private int quantity;

		public String getMerchName() {
			return merchName;
		}

		public void setMerchName(String merchName) {
			this.merchName = merchName;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

	}
}
