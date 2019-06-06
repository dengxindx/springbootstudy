package com.dx.springrabbitmqtest.single;

import com.dx.springrabbitmqtest.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 一个消息只能被一个消费者消费（非订阅）
 */
public class Recv {

    private final static String QUEUE_NAME = "test_1";

    public static void main(String[] argv) throws Exception {
        new Thread(() -> {
            try {
                consume(1);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                consume(2);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 消费
     */
    public static void consume(int i) throws IOException, TimeoutException, InterruptedException {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 同一时刻服务器只会发一条消息给消费者,配合消费者确认机制使用
        channel.basicQos(1);

        // 定义队列的消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列false表示需要开启channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false)手动确认
        channel.basicConsume(QUEUE_NAME, false, consumer);

        // 获取消息
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("消费者" + i + "号消费消息：" + message + "'");
            TimeUnit.SECONDS.sleep(i);

            //开启这行 表示使用手动确认模式（处理快的线程确认完成后继续处理下一个任务。能者多劳）
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }
}
