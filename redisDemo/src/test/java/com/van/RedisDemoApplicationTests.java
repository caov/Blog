package com.van;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.van.domain.User;
import com.van.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDemoApplicationTests {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testAdd() {
		userService.save(new User(1,"张三", "123456", 18));
	}
	
	@Test
	public void testQuery() {
		userService.findById(1);
	}
	
	@Test
	public void testUpdate() {
		User user = userService.findById(1);
		user.setUsername("李四");
		userService.update(user);
	}
	
	@Test
	public void testDelete() {
		userService.delete(1);
	}
}
