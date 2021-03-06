package com.chuansen.system.service.direct;

import com.chuansen.system.service.RabbitMQConnection;
import com.chuansen.system.service.util.Constant;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 订单消费者
 */
@SuppressWarnings("all")
public class OrderConsumer {

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("订单消费者...");
        // 创建连接
        Connection connection = RabbitMQConnection.getConnection();
        // 创建通道
        final Channel channel = connection.createChannel();
        // 队列需要去关联交换机
        channel.queueBind(Constant.DIRECT_ORDER_QUEUE_NAME,Constant.DIRECT_EXCHANGE_NAME, Constant.DIRECT_ORDER_KRY);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("订单消费者获取消息:" + msg);
            }
        };
        // 开始监听消息 自动签收
        channel.basicConsume(Constant.DIRECT_ORDER_QUEUE_NAME, true, defaultConsumer);

    }
}
