package com.dx.springrabbitmqtest.publish.fanout;

import com.dx.springrabbitmqtest.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


/**
 * 订阅模式, 这里测试两个不同队列绑定到同一交换机，消息会发送到所有绑定队列上，在同一队列上的消息只能有一个消费者消费
 */
public class Send {

    private static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();

        Channel channel = connection.createChannel();

        // 声明交换机，生产者绑定到交换机上,再分发到和交换机绑定的所有队列中.fanout交换机策略，子网广播
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        int i = 1;
        do {
            // 消息内容
            String message = "Hello World!" + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("生产消息：" + message + "'");
            i++;
        }while (i <= 50);

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
