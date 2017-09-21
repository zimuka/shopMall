package com.dayuanit.shoppingMall.service;

import com.dayuanit.shoppingMall.daomain.User;

public interface UserService {
	
	User getUser(int registId);
	
	void userInfor(String userPhone, String userSex, String userBirthday, int registId);
	
	void modifyUserInfor(Integer userId, String userPhone, String userSex, String userBirthday);
}
