package com.dayuanit.shoppingMall.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;

import java.util.List;

import com.dayuanit.shoppingMall.daomain.ShopCar;
import com.dayuanit.shoppingMall.mapper.ShopCarMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class ShopCarMapperTest {
	
	@Autowired
	private ShopCarMapper shopCarMapper;
	
	private ShopCar shopCar;
	
	@Before
	public void init() {
		shopCar = new ShopCar();
		shopCar.setStatus(1);
		shopCar.setMerchId(1);
		shopCar.setMerchNum(1);
		shopCar.setRegistId(1);
	}
	
	@Test
	@Rollback
	public void testAddShopCar() {
		int rows = shopCarMapper.addShopCar(shopCar);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testListShopCar() {
		shopCarMapper.addShopCar(shopCar);
		 List<ShopCar> shopCars = shopCarMapper.listShopCar(shopCar.getRegistId());
		assertEquals(1, shopCars.size());
		
	}
	
	@Test
	@Rollback
	public void testGetShopCarById() {
		shopCarMapper.addShopCar(shopCar);
		ShopCar otherShopCar = shopCarMapper.getShopCarById(shopCar.getId());
		assertEquals(otherShopCar.getId(), shopCar.getId());
	}
	
	@Test
	@Rollback
	public void testUpdateStatus() {
		shopCarMapper.addShopCar(shopCar);
		int rows = shopCarMapper.updateStatus(shopCar.getId());
		assertEquals(1, rows);
	}
	
}
