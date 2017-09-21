package com.dayuanit.shoppingMall.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shoppingMall.daomain.User;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.mapper.UserMapper;
import com.dayuanit.shoppingMall.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void userInfor(String userPhone, String userSex, String userBirthday, int registId) {
		
		User checkUser = userMapper.getUserByRegistId(registId);
		if (checkUser.getRegistId() != registId) {
			throw new ShoppingMallException("无权修改");
		}
		User user = new User();
		user.setUserBirthday(userBirthday);
		user.setUserSex(userSex);
		user.setUserPhone(userPhone);
		user.setRegistId(registId);
		
		userMapper.addUser(user);
	}

	@Override
	public User getUser(int registId) {
		User user = userMapper.getUserByRegistId(registId);
		return user;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void modifyUserInfor(Integer userId, String userPhone, String userSex, String userBirthday) {
		
		User user = userMapper.getUserById(userId);
		if (user.getId() != userId) {
			throw new ShoppingMallException("无权修改");
		}
		
		int rows = userMapper.updateUser(userId, userPhone, userSex, userBirthday);
		if (1 != rows) {
			throw new ShoppingMallException("修改失败");
		}
	}
	

}
