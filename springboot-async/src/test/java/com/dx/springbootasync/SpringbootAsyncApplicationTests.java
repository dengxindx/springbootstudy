package com.dx.springbootasync;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAsyncApplicationTests {

	@Autowired
	private Task task;

	public static void main(String[] args) throws InterruptedException {
		Task t = new Task();
		t.T1();
		t.T2();
		t.T3();
	}

	@Test
	public void test() throws InterruptedException {
		task.T1();
		task.T2();
		task.T3();
		Thread.sleep(1000 * 5);
	}

	@Test
	public void test2() throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		Future<String> t1 = task.T1();
		Future<String> t2 = task.T2();
		Future<String> t3 = task.T3();

		while (true){
			if(t1.isDone() && t2.isDone() && t3.isDone()) {
				// 三个任务都调用完成，退出循环等待
				break;
			}
			Thread.sleep(1000);
		}

		long end = System.currentTimeMillis();
		System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
	}
}
