package com.dx.springbootcacheredis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootCacheRedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootCacheRedisApplication.class, args);
	}

}
