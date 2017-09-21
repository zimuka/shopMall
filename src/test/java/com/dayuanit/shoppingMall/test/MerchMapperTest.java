package com.dayuanit.shoppingMall.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.assertEquals;
import com.dayuanit.shoppingMall.daomain.Merch;
import com.dayuanit.shoppingMall.mapper.MerchMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class MerchMapperTest {
	
	@Autowired
	private MerchMapper merchMapper;
	
	private Merch merch;
	
	@Before
	public void init() {
		merch = new Merch();
		merch.setCategoryId(1);
		merch.setMerchDescribe("");
		merch.setMerchName("");
		merch.setMerchPrice("11");
		merch.setMerchStock(1);
		merch.setPictureName("");
		merch.setSoldNum(2);
		merch.setStatus(1);
	}
	
	@Test
	@Rollback
	public void testAddMarch() {
		int rows = merchMapper.addMerch(merch);
		assertEquals(1, rows);
	}
	
	
	@Test
	@Rollback
	public void testGetMerchsByCategoryId() {
		List<Merch> listMerch = merchMapper.getMerchsByCategoryId(1);
		assertEquals(8, listMerch.size());
	}
	
	@Test
	@Rollback
	public void testGetMerchById() {
		merchMapper.addMerch(merch);
		Merch otherMerch = merchMapper.getMerchById(merch.getId());
		assertEquals(otherMerch.getId(), merch.getId());
	}
}
