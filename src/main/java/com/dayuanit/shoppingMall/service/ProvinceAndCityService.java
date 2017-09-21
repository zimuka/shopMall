package com.dayuanit.shoppingMall.service;

import java.util.List;
import java.util.Map;

public interface ProvinceAndCityService {
	List<Map<String, String>> listProvince();
	
	List<Map<String, String>> listCity(String provinveCode);
	
	List<Map<String, String>> listArea(String cityCode);
}
