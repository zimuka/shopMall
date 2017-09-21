package com.dayuanit.shoppingMall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shoppingMall.daomain.OrderDetail;

public interface OrderDetailMapper {
	int save(OrderDetail detail);
	
	List<OrderDetail> listOrderDetail(@Param("orderId") Integer orderId);
}
