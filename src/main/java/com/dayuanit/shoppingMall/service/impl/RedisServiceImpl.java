package com.dayuanit.shoppingMall.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.dayuanit.shoppingMall.redis.MallRedis;
import com.dayuanit.shoppingMall.service.RedisService;

import redis.clients.jedis.Jedis;

@Service
public class RedisServiceImpl implements RedisService{
	
	Logger log = LoggerFactory.getLogger(RedisServiceImpl.class);
	
	//判断缓存中是否有传来的key值
	@Override
	public boolean hasKey(String key) {
		Jedis jedis = null;
		try {
			jedis = MallRedis.getRedis();
			return jedis.exists(key);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}
	
	//如果缓存中已存在相应的key值，调用对应的方法将结果传递给终端
	@Override
	public List<Map<String, String>> getProvince(String key) {
		Jedis jedis = null;
		try {
			//获取连接池
			jedis = MallRedis.getRedis();
			//将通过key值得到的value,反序列化成List集合
			List list = JSON.parseArray(jedis.get(key), Map.class);
			return list;
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}

	@Override
	public void setProvince(String key, List<Map<String, String>> value) {
		Jedis jedis = null;
		try {
			jedis = MallRedis.getRedis();
			//将从前端得到的value值(终端传来的值为数组对象)序列化成json字符串
			String valueStr = JSON.toJSONString(value);
			log.info("保存省份 {}", value);
			//得到对应的value字符串后，将其一键值对的形式set到缓存redis中
			jedis.set(key, valueStr);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}

	@Override
	public List<Map<String, String>> getCity(String key) {
		Jedis jedis = null;
		try {
			jedis = MallRedis.getRedis();
			List list = JSON.parseArray(jedis.get(key), Map.class);
			return list;
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}

	@Override
	public void setCity(String key, List<Map<String, String>> value) {
		Jedis jedis = null;
		try {
			jedis = MallRedis.getRedis();
			String valueStr = JSON.toJSONString(value);
			log.info("城市保存{}", valueStr);
			jedis.set(key, valueStr);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}

	@Override
	public List<Map<String, String>> getArea(String key) {
		Jedis jedis = null;
		try {
			jedis = MallRedis.getRedis();
			List list = JSON.parseArray(jedis.get(key), Map.class);
			return list;
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}

	@Override
	public void setArea(String key, List<Map<String, String>> value) {
		Jedis jedis = null;
		try {
			jedis = MallRedis.getRedis();
			String valueStr = JSON.toJSONString(value);
			log.info("区域保存{}", valueStr);
			jedis.set(key, valueStr);
		} finally {
			if (null != jedis) {
				jedis.close();
			}
		}
	}

}
