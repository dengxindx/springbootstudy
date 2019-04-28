package com.dx.springbootasyncthreadpool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncThreadpoolApplicationTests {

	@Autowired
	private Task task;

	@Test
	public void contextLoads() throws InterruptedException {
		task.t1();
		task.t2();
		task.t3();

		Thread.currentThread().join();
	}

}
