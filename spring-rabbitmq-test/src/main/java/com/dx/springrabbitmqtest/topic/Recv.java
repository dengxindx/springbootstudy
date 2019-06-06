package com.dx.springrabbitmqtest.topic;

import com.dx.springrabbitmqtest.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {

    private static String EXCHANGE_NAME = "test_exchange_topic";

    private static String QUEUE_NAME = "test_queue_topic_work_1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "consume.*");

        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);

        System.out.println("topic:1号消费者等待消费中....");
        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("消费者1号:" + message);
            Thread.sleep(100);

            // 手动确认已经消费成功
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

    }
}
