package com.dayuanit.shoppingMall.service.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.shoppingMall.daomain.Regist;
import com.dayuanit.shoppingMall.eunms.RegistStatusEnum;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;
import com.dayuanit.shoppingMall.mapper.RegistMapper;
import com.dayuanit.shoppingMall.service.RegistService;

@Service
public class RegistServiceImpl implements RegistService{
	
	@Autowired
	private RegistMapper registMapper;
	
	@Transactional(rollbackFor=Exception.class)
	public void openAccount(String username, String password, String confirmPassword, String email) {
		if (null == username || "".equals(username)) {
			throw new ShoppingMallException("用户名不能为空");
		}
		
		if (null == password || "".equals(password)) {
			throw new ShoppingMallException("密码不能为空");
		}
		
		if (null == confirmPassword || "".equals(confirmPassword)) {
			throw new ShoppingMallException("密码不能为空");
		}
		
		if (!password.equals(confirmPassword)) {
			throw new ShoppingMallException("两次，密码输入不一致");
		}
		
		Regist checkRegist = registMapper.getRegistByUsername(username);
		if (checkRegist != null) {
			throw new ShoppingMallException("用户已存在");
		}
		
		//用户密码加密
		password += username;
		password = DigestUtils.md5Hex(password);
		
		Regist regist = new Regist();
		regist.setUsername(username);
		regist.setPassword(password);
		regist.setEmail(email);
		regist.setStatus(RegistStatusEnum.USABLE.getK());
		
		int rows = registMapper.addRegist(regist);
		if (1 != rows) {
			throw new ShoppingMallException("注册失败");
		}
	}
	
	
	public Regist login(String username, String password) {
		Regist regist = registMapper.getRegistByUsername(username);
		if (null == regist) {
			throw new ShoppingMallException("用户不存在或密码错误");
		}
		
		//用户密码加密
		password += username;
		password = DigestUtils.md5Hex(password);
		
		if (!username.equals(regist.getUsername())) {
			throw new ShoppingMallException("用户不存在或密码错误");
		}
		
		if (!password.equals(regist.getPassword())) {
			throw new ShoppingMallException("用户不存在或密码错误");
		}
		return regist;
	}


	@Override
	public Regist getRegistById(int registId) {
		Regist regist = registMapper.getRegistById(registId);
		return regist;
	}

}
