package com.dayuanit.shoppingMall.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface ProvinceAndCityMapper {
	List<Map<String, String>> listProvince();
	
	List<Map<String, String>> listCity(@Param("provinveCode") String provinveCode);
	
	List<Map<String, String>> listArea(@Param("cityCode") String cityCode);
}
