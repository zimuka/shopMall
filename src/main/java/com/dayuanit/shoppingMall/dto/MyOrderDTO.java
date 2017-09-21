package com.dayuanit.shoppingMall.dto;

import java.util.List;

public class MyOrderDTO {
	private int id;
	private String createTime;
	private String status;
	private String userRealName;
	private String payChannel;
	private String amount;
	private List<OrderMerchs> merchs;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public List<OrderMerchs> getMerchs() {
		return merchs;
	}

	public void setMerchs(List<OrderMerchs> merchs) {
		this.merchs = merchs;
	}

	public static class OrderMerchs {
		private String merchName;
		private String price;
		private int num;


		public String getMerchName() {
			return merchName;
		}

		public void setMerchName(String merchName) {
			this.merchName = merchName;
		}

		public String getPrice() {
			return price;
		}

		public void setPrice(String price) {
			this.price = price;
		}

		public int getNum() {
			return num;
		}

		public void setNum(int num) {
			this.num = num;
		}

	}
}
