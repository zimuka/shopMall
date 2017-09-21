package com.dayuanit.shoppingMall.mapper;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shoppingMall.daomain.Regist;

public interface RegistMapper {
	
	int addRegist(Regist regist);
	
	Regist getRegistByUsername(@Param("username") String username);
	
	Regist getRegistById(@Param("registId") Integer registId);
}
