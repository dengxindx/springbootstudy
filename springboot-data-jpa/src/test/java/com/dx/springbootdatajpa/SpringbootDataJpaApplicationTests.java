package com.dx.springbootdatajpa;

import com.dx.springbootdatajpa.domain.User;
import com.dx.springbootdatajpa.domain.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootDataJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void contextLoads() {
		userRepository.save(new User("张三", 23));

		userRepository.save(new User("AAA", 10));
		userRepository.save(new User("BBB", 20));
		userRepository.save(new User("CCC", 30));
		userRepository.save(new User("DDD", 40));
		userRepository.save(new User("EEE", 50));
		userRepository.save(new User("FFF", 60));
		userRepository.save(new User("GGG", 70));
		userRepository.save(new User("HHH", 80));
		userRepository.save(new User("III", 90));
		userRepository.save(new User("JJJ", 100));

		List<User> all = userRepository.findAll();
		System.out.println(all);
		System.out.println("***************************");

		Integer fff = userRepository.findByName("FFF").getAge();
		System.out.println("姓名为FFF的年龄：" + fff);
		System.out.println("***************************");

		Integer fff2 = userRepository.findUser("FFF").getAge();
		System.out.println("姓名为FFF的年龄：" + fff2);
		System.out.println("***************************");

		User user = userRepository.findByNameAndAge("张三", 23);
		System.out.println(user);
		System.out.println("***************************");

		int size = userRepository.findAll().size();
		System.out.println("没有删除前：" + size);
		System.out.println("***************************");

		userRepository.delete(userRepository.findByName("AAA"));

		int size1 = userRepository.findAll().size();
		System.out.println("删除后：" + size1);
	}

}
