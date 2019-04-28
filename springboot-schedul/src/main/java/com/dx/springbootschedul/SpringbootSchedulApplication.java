package com.dx.springbootschedul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootSchedulApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSchedulApplication.class, args);
	}

}
