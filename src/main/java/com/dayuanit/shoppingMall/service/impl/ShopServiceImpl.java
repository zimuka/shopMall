package com.dayuanit.shoppingMall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shoppingMall.daomain.Regist;
import com.dayuanit.shoppingMall.daomain.ShopCar;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.mapper.RegistMapper;
import com.dayuanit.shoppingMall.mapper.ShopCarMapper;
import com.dayuanit.shoppingMall.service.ShopService;

@Service
public class ShopServiceImpl implements ShopService{
	
	@Autowired
	private ShopCarMapper shopCarMapper;
	
	@Autowired
	private RegistMapper registMapper;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void joinShopCar(int registId, int merchId, int merchNum) {
		Regist registOther = registMapper.getRegistById(registId);
		if (null == registOther.getId()) {
			throw new ShoppingMallException("用户没有登录");
		}
		ShopCar shopCar = new ShopCar();
		shopCar.setRegistId(registId);
		shopCar.setMerchId(merchId);
		shopCar.setMerchNum(merchNum);
		
		int rows = shopCarMapper.addShopCar(shopCar);
		if (1 != rows) {
			throw new ShoppingMallException("增加购物车失败");
		}
	}

	@Override
	public List<ShopCar> getShopCars(int registId) {
		Regist regist = registMapper.getRegistById(registId);
		
		if (regist.getId() != registId){
			throw new ShoppingMallException("无权查看");
		}
		
		List<ShopCar> shopCarDetails = shopCarMapper.listShopCar(registId);
		return shopCarDetails;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delectShopCar(int registId, int shopCarId) {
		Regist regist = registMapper.getRegistById(registId);
		if (regist.getId() != registId) {
			throw new ShoppingMallException("无权查看");
		}
		
		ShopCar shopCar = shopCarMapper.getShopCarById(shopCarId);
		if (shopCarId != shopCar.getId()) {
			throw new ShoppingMallException("无权删除");
		}
		
		int rows = shopCarMapper.updateStatus(shopCarId);
		if (1 != rows) {
			throw new ShoppingMallException("删除失败");
		}
	}
	

}
