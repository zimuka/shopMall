package com.dayuanit.shoppingMall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shoppingMall.daomain.Merch;

public interface MerchMapper {
	
	int addMerch(Merch merch);
	
	List<Merch> getMerchsByCategoryId(@Param("CategoryId") Integer CategoryId);
	
	Merch getMerchById(@Param("merchId") Integer merchId);
}
