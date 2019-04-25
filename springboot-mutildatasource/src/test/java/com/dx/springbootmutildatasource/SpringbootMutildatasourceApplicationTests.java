package com.dx.springbootmutildatasource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * springboot多数据源对jdbctemplate的支持
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMutildatasourceApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	JdbcTemplate jdbcTemplate2;

	@Before
	public void setUp() {
		jdbcTemplate1.update("DELETE  FROM  USER ");
		jdbcTemplate2.update("DELETE  FROM  USER ");
	}

	@Test
	public void jdbcTemplateTest() {
		// 往第一个数据源中插入两条数据
		jdbcTemplate1.update("insert into user(id, name, age) values(?,?,?)", 1, "张三", 23);
		jdbcTemplate1.update("insert into user(id, name, age) values(?,?,?)", 2, "李四", 24);

		// 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
		jdbcTemplate2.update("insert into user(id, name, age) values(?,?,?)", 1, "张三", 23);

		// 查一下第一个数据源中是否有两条数据，验证插入是否成功
		Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from user", String.class));

		// 查一下第一个数据源中是否有两条数据，验证插入是否成功
		Assert.assertEquals("1", jdbcTemplate2.queryForObject("select count(1) from user", String.class));

	}
}
