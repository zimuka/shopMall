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
import com.dayuanit.shoppingMall.daomain.Regist;
import com.dayuanit.shoppingMall.mapper.RegistMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegistMapperTest {
	
	@Autowired
	private RegistMapper registMapper;
	
	private Regist regist;
	
	@Before
	public void init() {
		regist = new Regist();
		regist.setUsername("007");
		regist.setPassword("1");
		regist.setStatus(1);
		regist.setEmail("666@163.com");
	}
	
	@Test
	@Rollback
	public void testAddRegist() {
		int rows = registMapper.addRegist(regist);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testGetRegistByUsername() {
		registMapper.addRegist(regist);
		Regist otherRegist = registMapper.getRegistByUsername(regist.getUsername());
		assertEquals(otherRegist.getUsername(), regist.getUsername());
	}
	
	@Test
	@Rollback
	public void testGetRegistById() {
		registMapper.addRegist(regist);
		Regist otherRegist = registMapper.getRegistById(regist.getId());
		assertEquals(otherRegist.getId(), regist.getId());
	}
}
