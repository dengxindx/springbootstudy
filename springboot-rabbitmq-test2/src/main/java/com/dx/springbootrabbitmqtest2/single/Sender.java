package com.dx.springbootrabbitmqtest2.single;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * 生产者
 */
@Component("singleSender1")
public class Sender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    public void send () throws InterruptedException {
        int i = 1;
        do {
            String message = "hello world" + i;
            System.out.println("生产消息:" + message);
            rabbitTemplate.convertAndSend(queue.getName(), message);
//            TimeUnit.SECONDS.sleep(1);
            i++;
        }while (i <= 10);

    }
}
