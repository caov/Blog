package com.van.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.van.dao.UserRepository;
import com.van.domain.User;
import com.van.utils.JsonUtil;
import com.van.utils.RedisUtils;

@Service
public class UserService extends RedisUtils{
	
	@Autowired
    private UserRepository userRepository;
	
	/**
	 * @author van
	 * 添加
	 */
	public void save(User user) {
		userRepository.save(user);
	}
	
	/**
	 * @author van
	 * 查找，先判断redis缓存；有，从缓存获取；没有，从数据库获取
	 */
	public User findById(int id) {
		String key = String.valueOf(id);
		boolean hasKey = hasKey(key);
		
		if (hasKey) {
			System.out.println("=========从缓存中获取=============");
			return JsonUtil.toObject(get(key), User.class);
		}
		
		System.out.println("=========从数据库中获取=============");
		User user = userRepository.findOne(id);
		set(key, JsonUtil.toJson(user));
		return user;
	}
	
	/**
	 * @author van
	 * 更新
	 */
	public void update(User user) {
		userRepository.save(user);
		
		//从缓存中删除
		String key = String.valueOf(user.getId());
		delete(key);
	}
	
	/**
	 * @author van
	 * 删除
	 */
	public void delete(int id) {
		userRepository.delete(id);
		
		//从缓存中删除
		String key = String.valueOf(id);
		delete(key);
	}
}
