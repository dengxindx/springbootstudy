package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CacheManager cacheManager;

	@Before
	public void before() {
		// 不加事务也可执行
		userRepository.save(new User("张三", 23));
		userRepository.save(new User("李四", 24));
		userRepository.save(new User("王五", 25));
	}

	@Test
	public void test() {
		// 不加事务也可执行
		User user1 = userRepository.findByName("张三");
		System.out.println("第一次查询：" + user1);

		User user2 = userRepository.findByName("张三");
		System.out.println("第二次查询：" + user2);
	}

	@Test
	public void test2() {
		// 不加事务也可执行
		User user1 = userRepository.findUser("李四");
		System.out.println("第一次查询：" + user1);

		User user2 = userRepository.findUser("李四");
		System.out.println("第二次查询：" + user2);
	}

	@Test
	public void test3() {
		// 需要添加事务
		userRepository.updateUser("赵六", "张三");
	}

	@Test
	public void test4() {
		// 不加事务也可执行
		userRepository.deleteAll();
	}
}
