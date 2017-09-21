package com.dayuanit.shoppingMall.service;

import com.dayuanit.shoppingMall.daomain.Regist;

public interface RegistService {
	
	void openAccount(String username, String password, String confirmPassword, String email);
	
	Regist login(String username, String password);
	
	Regist getRegistById(int registId);
	
}
