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
import com.dayuanit.shoppingMall.daomain.OrderDetail;
import com.dayuanit.shoppingMall.mapper.OrderDetailMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderDetailMapperTest {
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	private OrderDetail orderDetail;
	
	@Before
	public void init() {
		orderDetail = new OrderDetail();
		orderDetail.setAmount("220");
		orderDetail.setMerchId(2);
		orderDetail.setMerchName("家电");
		orderDetail.setOrderId(1);
		orderDetail.setQuantity(22);
		orderDetail.setUnitPrice("10");
	}
	
	@Test
	@Rollback
	public void testSave() {
		int rows = orderDetailMapper.save(orderDetail);
		assertEquals(1, rows);
	}
	
}
