package com.cy;

import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
/**
 * @EnableAsync 注解表示项目中支持异步操作(让
 * 异步的自动配置生效).
 * @EnableCaching 注解表示启动缓存配置
 */
@EnableAsync
@EnableCaching
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
