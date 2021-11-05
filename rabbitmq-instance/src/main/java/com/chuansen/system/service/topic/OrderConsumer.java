package com.chuansen.system.service.topic;

import com.chuansen.system.service.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订单消费者
 */
public class OrderConsumer {
    /**
     * 定义短信队列
     */
    private static final String QUEUE_NAME = "topic_order_queue";
    /**
     * 定义交换机的名称
     */
    private static final String EXCHANGE_NAME = "topic_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("订单消费者...");
        // 创建连接
        Connection connection = RabbitMQConnection.getConnection();
        // 创建通道
        final Channel channel = connection.createChannel();
        // 消费者关联队列
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "order.*");
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("订单消费者获取消息:" + msg);
            }
        };
        // 开始监听消息 自动签收
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

    }
}
