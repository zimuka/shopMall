package com.dayuanit.shoppingMall.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.shoppingMall.mapper.ProvinceAndCityMapper;
import com.dayuanit.shoppingMall.service.ProvinceAndCityService;
import com.dayuanit.shoppingMall.service.RedisService;

@Service
public class ProvinceAndServiceImpl implements ProvinceAndCityService{
	
	@Autowired
	private ProvinceAndCityMapper proviceAndCity;
	
	//@Autowired
	@Resource(name="redisTempServiceImpl")
	private RedisService redisService;
	
	Logger log = LoggerFactory.getLogger(ProvinceAndServiceImpl.class);
	
	@Override
	public List<Map<String, String>> listProvince() {
		//首先定义key值，建议定义成   项目名+功能名+业务逻辑名
		String provinceKey = "mall:province";
		
		//首先在缓存中查找是否存在对应的key值，如果有，在缓存中查询，返回给终端，如果没有查询数据库
		boolean flag = redisService.hasKey(provinceKey);
		if (flag) {
			log.info("省份走缓存>>>>>>");
			//如果在缓存中已经存在key值，直接调用redisService中的get方法，将结果返回给终端
			return redisService.getProvince(provinceKey);
		}
		
		//如果没有对应的key值，则需要在数据库中查询，查询完后，为方便以后查询，将其结果以value的形式set到缓存中
		List<Map<String, String>> provinceDate = proviceAndCity.listProvince();
		
		redisService.setProvince(provinceKey, provinceDate);
		return provinceDate;
	}

	@Override
	public List<Map<String, String>> listCity(String provinveCode) {
		//此处需要带上对应的省的code值
		String cityKey = String.format("mall:city:%s", provinveCode);
		boolean flag = redisService.hasKey(cityKey);
		if (flag) {
			log.info("城市走缓存>>>>>>>");
			return redisService.getCity(cityKey);
		}
		
		List<Map<String, String>> cityDate = proviceAndCity.listCity(provinveCode);
		
		redisService.setCity(cityKey, cityDate);
		return cityDate;
	}

	@Override
	public List<Map<String, String>> listArea(String cityCode) {
		//带上对应的cityCode值
		String areaKry = String.format("mall:area:%s", cityCode);
		boolean flag = redisService.hasKey(areaKry);
		if (flag) {
			log.info("区县走缓存>>>>>>");
			return redisService.getArea(areaKry);
		}
		
		List<Map<String, String>> areaDate = proviceAndCity.listArea(cityCode);
		redisService.setArea(areaKry, areaDate);
		
		return areaDate;
	}

}
