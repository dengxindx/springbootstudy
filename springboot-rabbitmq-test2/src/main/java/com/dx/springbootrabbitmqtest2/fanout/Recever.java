package com.dx.springbootrabbitmqtest2.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component("fanoutRecever")
public class Recever {

    /**
     * 创建3个消费者
     */

    @RabbitListener(queues = "q_fanout_A")
    public void process(String hello) {
        System.out.println("AReceiver  : " + hello + "/n");
    }

    @RabbitListener(queues = "q_fanout_B")
    public void process2(String hello) {
        System.out.println("BReceiver  : " + hello + "/n");
    }

    @RabbitListener(queues = "q_fanout_C")
    public void process3(String hello) {
        System.out.println("CReceiver  : " + hello + "/n");
    }
}
