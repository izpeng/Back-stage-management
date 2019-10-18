package com.cy.pj.common.config;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
/**
 * @ConfigurationProperties 注解表示读取配置文件中
 * 以某个名字作为前缀配置信息，然后将读到配置信息，通过类
 * 中的set方法赋值属性。
 */
@Slf4j
@Setter
@ConfigurationProperties("async-thread-pool")
@Configuration
public class SpringAsyncConfig implements AsyncConfigurer {
	private Integer corePoolSize;
	private Integer maxPoolSize;
	private Integer keepAliveSeconds;
	private Integer queueCapacity;
	
	//构建线程工厂(其目的主要是为池线程对象起个名字)
	private ThreadFactory threadFactory=new ThreadFactory() {
		//线程安全原子操作对象(底层CAS算法，基于CPU硬件实现)
		private AtomicLong at=new AtomicLong(1);
		public Thread newThread(Runnable r) {
			return new Thread(r, "db-project-thread-"+at.getAndIncrement());
		};
	};
	//业务对象中使用@Async注解时，系统底层默认使用此方法创建
	//的池对象
	@Override
	public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor pExecutor=
        new ThreadPoolTaskExecutor();
        //设置核心线程数
        pExecutor.setCorePoolSize(corePoolSize);
        //设置池中最大线程数
        pExecutor.setMaxPoolSize(maxPoolSize);
        //设置线程最大空闲实现
        pExecutor.setKeepAliveSeconds(keepAliveSeconds);
        //设置队列容量
        pExecutor.setQueueCapacity(queueCapacity);
        //设置线程工程
        pExecutor.setThreadFactory(threadFactory);
        //设置拒绝执行的策略
        pExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				log.error("任务已满");
			}
		});
        pExecutor.initialize();
		return pExecutor;
	}
	//记录任务执行过程中的异常。
	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {
			@Override
			public void handleUncaughtException(Throwable ex, Method method, Object... params) {
				log.error("任务执行时出现了未知的异常",ex);
			}
		};
	}
}
