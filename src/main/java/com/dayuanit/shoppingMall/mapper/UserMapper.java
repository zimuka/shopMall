package com.dayuanit.shoppingMall.mapper;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.shoppingMall.daomain.User;

public interface UserMapper {
	
	int addUser(User user);
	
	User getUserByRegistId(@Param("registId") Integer registId);
	
	User getUserById(@Param("userId") Integer userId);
	
	int updateUser(@Param("userId") Integer userId,
				   @Param("userPhone") String userPhone,
				   @Param("userSex") String userSex,
				   @Param("userBirthday") String userBirthday);
}
