package com.dayuanit.shoppingMall.service;

import java.util.List;
import java.util.Map;

public interface RedisService {
	//判断前端传来的值在缓存中是否存在
	boolean hasKey(String key);
	
	//通过key值得到对应的省份,将其传递给终端
	List<Map<String, String>> getProvince(String key);
	
	//如果在终端传来的信息中没有对应的key值，则将从数据库中查询到的值set到缓存redis中
	void setProvince(String key, List<Map<String, String>> value);
	
	//通过key值得到对应的城市，将其值传递给终端
	List<Map<String, String>> getCity(String key);
	
	//如果在终端传来的信息中没有对应的Key值，则将从数据库中查询到的值set到缓存redis中
	void setCity(String key, List<Map<String, String>> value);
	
	List<Map<String, String>> getArea(String key);
	
	void setArea(String key, List<Map<String, String>> value);
}
