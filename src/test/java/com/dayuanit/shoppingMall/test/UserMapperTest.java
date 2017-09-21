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
import com.dayuanit.shoppingMall.daomain.User;
import com.dayuanit.shoppingMall.mapper.UserMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	private User user;
	
	@Before
	public void init() {
		user = new User();
		user.setRegistId(1);
		user.setUserSex("男");
		user.setUserPhone("112");
		user.setUserBirthday("1990-01-01");
	}
	
	@Test
	@Rollback
	public void testAddUser() {
		int rows = userMapper.addUser(user);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testGetUserByRegistId() {
		userMapper.addUser(user);
		User otherUser = userMapper.getUserByRegistId(user.getRegistId());
		assertEquals(otherUser.getRegistId(), user.getRegistId());
	}
	
	@Test
	@Rollback
	public void testUpdateUser() {
		userMapper.addUser(user);
		int rows = userMapper.updateUser(user.getId(), "666", "女", "1999-05-24");
		assertEquals(1, rows);
	}
}
