package org.dubboService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(App.class, args);
		ServiceProxy proxy = ctx.getBean(ServiceProxy.class);
		System.out.println(proxy.helloService.hello("笑傲江湖"));// 调用服务
	}
}
