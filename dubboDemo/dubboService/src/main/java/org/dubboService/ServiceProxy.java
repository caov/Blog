package org.dubboService;

import org.dubboProvider.HelloService;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;

@Component
public class ServiceProxy {
	
	@Reference
	public HelloService helloService;
}
