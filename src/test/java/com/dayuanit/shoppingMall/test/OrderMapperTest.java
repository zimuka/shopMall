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
import com.dayuanit.shoppingMall.daomain.Order;
import com.dayuanit.shoppingMall.mapper.OrderMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class OrderMapperTest {
	
//	@Autowired
//	private OrderMapper orderMapper;
//	
//	private Order order;
//	
//	@Before
//	public void init() {
//		order = new Order();
//		order.setAmount("666");
//		order.setOrderFrom(1);
//		order.setStatus(1);
//		order.setRegistId(2);
//		order.setPayChannel(1);
//	}
//	
//	@Test
//	@Rollback
//	public void testAddOrder() {
//		int rows = orderMapper.addOrder(order);
//		assertEquals(1, rows);
//	}
}
