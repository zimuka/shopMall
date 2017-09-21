package com.dayuanit.shoppingMall.service;

import java.util.List;

import com.dayuanit.shoppingMall.daomain.ShopCar;

public interface ShopService {
	
	void joinShopCar(int registId, int merchId, int merchNum);
	
	List<ShopCar> getShopCars(int registId);
	
	void delectShopCar(int registId, int shopCarId);
}
