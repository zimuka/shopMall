package com.dayuanit.shoppingMall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shoppingMall.daomain.Order;

public interface OrderMapper {
	int addOrder(Order order);
	
	Order getOrderById(@Param("orderId") Integer orderId);
	
	int changeOrderStatus(@Param("status") Integer status, @Param("registId") Integer registId, @Param("orderId") Integer orderId);

	int updateOrderInfo(Order order);
	
	List<Order> listOrderByRegistIdAndStauts(@Param("registId") Integer registId,
											 @Param("status") Integer status,
											 @Param("offset") Integer offset,
											 @Param("pageNum") Integer pageNum);
	
	int countOrderNum(@Param("registId") Integer registId, @Param("status") Integer status);
}
