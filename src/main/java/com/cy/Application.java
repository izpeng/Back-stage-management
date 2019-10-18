package com.cy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
@EnableAsync     // 项目中支持异步操作 默认不生效
@SpringBootApplication
@EnableCaching   //启动缓存配置
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
