package com.dx.springbootrabbitmqtest2;

import com.dx.springbootrabbitmqtest2.single.Sender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqTest2ApplicationTests {

	@Autowired
	private Sender sender;

	@Test
	public void contextLoads() throws InterruptedException {
		sender.send();
	}

}
