package com.van.utils;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class RedisUtils {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
    
    @Resource(name = "redisTemplate")
	ValueOperations<String, String> stringOps;
    
    //判断key是否存在
	public Boolean hasKey(String key) {
		return redisTemplate.hasKey(key);
	}
	
	//得到key中value
	public String get(String key) {
		return stringOps.get(key);
	}
	
	//存值
	public void set(String key, String value) {
		stringOps.set(key, value);
	}
	
	//删除值
	public void delete(String key) {
		redisTemplate.delete(key);
	}
	
}
