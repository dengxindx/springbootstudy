package com.dx.springbootrabbitmqmytest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRabbitmqMytestApplicationTests {

	@Autowired
	private Sender sender;

	private Receiver receiverl;

	@Test
	public void sendT1() {
		sender.send();
	}
}
