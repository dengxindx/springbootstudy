package com.dx.springbootmongodb;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMongodbApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	private UserRepository userRepository;

//	@Before
	public void setUp() {
		userRepository.deleteAll();
	}

	@Test
	public void test() throws Exception {
		User u2 = userRepository.findByUsername("张三");
		System.out.println(u2);
//		// 创建三个User，并验证User总数
//		userRepository.save(new User(1L, "didi", 30));
//		userRepository.save(new User(2L, "mama", 40));
//		userRepository.save(new User(3L, "kaka", 50));
//
//		User kaka = userRepository.findByUsername("kaka");
//		System.out.println("kaka的年龄：" + kaka.getAge());
//
//		Assert.assertEquals(3, userRepository.findAll().size());
//
//		// 删除一个User，再验证User总数
//		User u2 = userRepository.findByUsername("mama");
//		userRepository.delete(u2);
//		Assert.assertEquals(2, userRepository.findAll().size());
//
//		// 删除一个User，再验证User总数
//		userRepository.deleteById(1L);
//		Assert.assertEquals(1, userRepository.findAll().size());
//
//		userRepository.save(new User(4L, "张三", 23));
	}

}
