package com.dx.springbootasyncthreadpoolclose;

import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncThreadpoolCloseApplicationTests {

	@Autowired
	private Task task;

	@Test
	@SneakyThrows
	public void test() {

		for (int i = 0; i < 10000; i++) {
			task.doTaskOne();
			task.doTaskTwo();
			task.doTaskThree();
		}
	}

}
