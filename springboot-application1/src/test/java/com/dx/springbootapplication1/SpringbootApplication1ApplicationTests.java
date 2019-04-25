package com.dx.springbootapplication1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplication1ApplicationTests {

	@Autowired
	BolgProperties bolgProperties;

	@Test
	public void contextLoads() {
		String name = bolgProperties.getName();
		String titile = bolgProperties.getTitile();
		String des = bolgProperties.getDes();

		System.out.println("name=" + name);
		System.out.println("titile=" + titile);
		System.out.println("des=" + des);
	}

}
