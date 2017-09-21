package com.dayuanit.shoppingMall.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.dayuanit.shoppingMall.service.RedisService;

@Service("redisTempServiceImpl")
public class RedisTempServiceImpl implements RedisService{
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Resource(name="redisTemplate")
	private ValueOperations<String, List<Map<String, String>>> provinceOper;
	
	@Override
	public boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}

	@Override
	public List<Map<String, String>> getProvince(String key) {
		return provinceOper.get(key);
	}

	@Override
	public void setProvince(String key, List<Map<String, String>> value) {
		provinceOper.set(key, value);
	}

	@Override
	public List<Map<String, String>> getCity(String key) {
		return provinceOper.get(key);
	}

	@Override
	public void setCity(String key, List<Map<String, String>> value) {
		provinceOper.set(key, value);
	}

	@Override
	public List<Map<String, String>> getArea(String key) {
		return provinceOper.get(key);
	}

	@Override
	public void setArea(String key, List<Map<String, String>> value) {
		provinceOper.set(key, value);
	}

}
