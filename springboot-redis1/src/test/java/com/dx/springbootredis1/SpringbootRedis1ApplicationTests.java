package com.dx.springbootredis1;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedis1ApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Test
	public void contextLoads() {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		System.out.println("----------------------" + stringRedisTemplate.opsForValue().get("aaa"));
		Assert.assertEquals("111", stringRedisTemplate.opsForValue().get("aaa"));
	}

	@Autowired
	private RedisTemplate<String, User> redisTemplate;

	@Test
	public void test() {
		User user = new User("张三", 23);
		redisTemplate.opsForValue().set(user.getUsername(), user);

		User user2 = new User("李四", 24);
		redisTemplate.opsForValue().set(user.getUsername(), user2);

		System.out.println("张三年龄：" + redisTemplate.opsForValue().get("张三").getAge().longValue());

//		Assert.assertEquals(20, redisTemplate.opsForValue().get("张三").getAge().longValue());
//		Assert.assertEquals(24, redisTemplate.opsForValue().get("李四").getAge().longValue());
	}
}
