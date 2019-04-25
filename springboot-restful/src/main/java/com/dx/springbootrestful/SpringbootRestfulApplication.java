package com.dx.springbootrestful;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dx.springbootrestful.dao")
public class SpringbootRestfulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootRestfulApplication.class, args);
	}

}
