package com.dx.springrabbitmqtest.single;

import com.dx.springrabbitmqtest.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.util.concurrent.TimeUnit;

/**
 * 一个消息只能被一个消费者消费（非订阅）
 */
public class Send {

    private final static String QUEUE_NAME = "test_1";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();

        // 声明（创建）队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        int i = 1;
        do {
            // 消息内容
            String message = "Hello World!" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("生产消息：" + message + "'");
            i++;
//            TimeUnit.SECONDS.sleep(1);
        }while (i <= 100);

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
