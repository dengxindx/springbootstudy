package com.dx.springbootasyncthreadpool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
public class SpringbootAsyncThreadpoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootAsyncThreadpoolApplication.class, args);
	}

	/**
	 * 自定义一个线程池
	 * @EnableAsync 开启异步
	 * @Configuration 自动扫描
	 */
	@EnableAsync
	@Configuration
	class TaskPoolConfig {

		@Bean("taskExecutor")
		public Executor taskExecutor() {
			ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
			executor.setCorePoolSize(10);
			executor.setMaxPoolSize(20);
			executor.setQueueCapacity(200);
			executor.setKeepAliveSeconds(60);
			executor.setThreadNamePrefix("myExecutor-");
			executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
			return executor;
		}
	}
}
