package com.dx.springbootmutildatasourcedatajpa;

import com.dx.springbootmutildatasourcedatajpa.domain.p.User;
import com.dx.springbootmutildatasourcedatajpa.domain.p.UserRepository;
import com.dx.springbootmutildatasourcedatajpa.domain.s.Message;
import com.dx.springbootmutildatasourcedatajpa.domain.s.MessageRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMutildatasourceDataJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MessageRepository messageRepository;

	@Test
	public void test2() throws Exception {
		userRepository.save(new User("赵六", 25));
	}

	@Test
	public void test() throws Exception {

		userRepository.save(new User("aaa", 10));
		userRepository.save(new User("bbb", 20));
		userRepository.save(new User("ccc", 30));
		userRepository.save(new User("ddd", 40));
		userRepository.save(new User("eee", 50));

		Assert.assertEquals(5, userRepository.findAll().size());

		messageRepository.save(new Message("o1", "aaaaaaaaaa"));
		messageRepository.save(new Message("o2", "bbbbbbbbbb"));
		messageRepository.save(new Message("o3", "cccccccccc"));

		Assert.assertEquals(3, messageRepository.findAll().size());

	}

}
