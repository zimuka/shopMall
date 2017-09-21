package com.dayuanit.shoppingMall.daomain;

import java.util.Date;

public class Merch {
	private Integer id;
	private Integer categoryId;
	private String merchName;
	private String merchPrice;
	private Integer merchStock;
	private String merchDescribe;
	private Integer status;
	private Integer soldNum;
	private Date createTime;
	private Date modifyTime;
	private String pictureName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getMerchName() {
		return merchName;
	}

	public void setMerchName(String merchName) {
		this.merchName = merchName;
	}

	public String getMerchPrice() {
		return merchPrice;
	}

	public void setMerchPrice(String merchPrice) {
		this.merchPrice = merchPrice;
	}

	public Integer getMerchStock() {
		return merchStock;
	}

	public void setMerchStock(Integer merchStock) {
		this.merchStock = merchStock;
	}

	public String getMerchDescribe() {
		return merchDescribe;
	}

	public void setMerchDescribe(String merchDescribe) {
		this.merchDescribe = merchDescribe;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSoldNum() {
		return soldNum;
	}

	public void setSoldNum(Integer soldNum) {
		this.soldNum = soldNum;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getPictureName() {
		return pictureName;
	}

	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}

}
