package com.dayuanit.shoppingMall.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shoppingMall.daomain.ShopCar;

public interface ShopCarMapper {
	
	int addShopCar(ShopCar shopCar);
	
	List<ShopCar> listShopCar(@Param("registId") Integer registId);
	
	int updateStatus(@Param("shopCarId") Integer shopCarId);
	
	ShopCar getShopCarById(@Param("shopCarId") Integer shopCarId);
}
