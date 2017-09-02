package com.van.utils;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching //启用缓存，这个注解很重要
public class RedisObjectSerializer extends CachingConfigurerSupport {
    
    @Bean
    public CacheManager cacheManager(RedisTemplate<?,?> redisTemplate) {
           CacheManager cacheManager = new RedisCacheManager(redisTemplate);
           return cacheManager;
    }
    
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
           RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
           redisTemplate.setConnectionFactory(factory);
           
           //使用String序列化
           RedisSerializer<String> stringSerializer = new StringRedisSerializer();
           redisTemplate.setKeySerializer(stringSerializer);
           redisTemplate.setValueSerializer(stringSerializer);
           return redisTemplate;

    }
}
