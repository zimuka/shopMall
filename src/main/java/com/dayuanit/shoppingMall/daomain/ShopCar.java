package com.dayuanit.shoppingMall.daomain;

import java.util.Date;

public class ShopCar {
	private Integer id;
	private Integer registId;
	private Integer merchId;
	private Integer merchNum;
	private Integer status;
	private Date createTime;
	private Date modifyTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRegistId() {
		return registId;
	}

	public void setRegistId(Integer registId) {
		this.registId = registId;
	}

	public Integer getMerchId() {
		return merchId;
	}

	public void setMerchId(Integer merchId) {
		this.merchId = merchId;
	}

	public Integer getMerchNum() {
		return merchNum;
	}

	public void setMerchNum(Integer merchNum) {
		this.merchNum = merchNum;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

}
