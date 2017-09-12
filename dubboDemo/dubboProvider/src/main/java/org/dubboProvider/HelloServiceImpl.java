package org.dubboProvider;

import com.alibaba.dubbo.config.annotation.Service;

@Service
public class HelloServiceImpl implements HelloService{

	@Override
	public String hello(String name) {
		System.out.println("调用了这个服务");
		return String.format("hi , %s!", name);
	}

}
