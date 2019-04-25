package com.dx.springbootfreemarker;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dx.springbootfreemarker.dao")
public class SpringbootFreemarkerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootFreemarkerApplication.class, args);
	}

}
