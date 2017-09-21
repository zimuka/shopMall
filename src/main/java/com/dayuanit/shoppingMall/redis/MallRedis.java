package com.dayuanit.shoppingMall.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class MallRedis {
	private static final Logger log = LoggerFactory.getLogger(MallRedis.class);
	
	private static final JedisPool pool = new JedisPool(new JedisPoolConfig(), "10.10.10.101");
	
	public static Jedis getRedis() {
		return pool.getResource();
	}
	
	public static void setValue(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			jedis.set(key, value);
		} finally {
			if (null != jedis) {
				jedis.close();
				
			}
		}
	}
	
	public static String getValue(String key) {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
			return jedis.get(key);
		} finally{
			if (null != jedis) {
				jedis.close();
			}
		}
	}
	
	public static void main(String[] args) {
		MallRedis.setValue("测试", "988");
		
		log.info(MallRedis.getValue("测试"));
	}
}	
