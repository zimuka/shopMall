package com.dayuanit.shoppingMall.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.dayuanit.shoppingMall.daomain.Regist;
import com.dayuanit.shoppingMall.exception.ShoppingMallException;

public class BaseController {
	protected static final String LOGIN_FLAG = "regist";

	protected Regist getUser(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (null == session) {
			throw new ShoppingMallException("用户未登录");
		}
		
		Object obj = session.getAttribute(LOGIN_FLAG);
		if (null == LOGIN_FLAG) {
			throw new ShoppingMallException("用户未登录");
		}
		Regist regist = (Regist)obj;
		
		return regist;
	}
	
	protected int getUserById(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (null == session) {
			throw new ShoppingMallException("用户未登录");
		}
		
		Object obj = session.getAttribute(LOGIN_FLAG);
		if (null == LOGIN_FLAG) {
			throw new ShoppingMallException("用户未登录");
		}
		Regist regist = (Regist)obj;
		
		return regist.getId();
	}
	
	protected String getUserByName(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if (null == session) {
			throw new ShoppingMallException("用户未登录");
		}
		
		Object obj = session.getAttribute(LOGIN_FLAG);
		if (null == LOGIN_FLAG) {
			throw new ShoppingMallException("用户未登录");
		}
		Regist regist = (Regist)obj;
		
		return regist.getUsername();
	}
}
