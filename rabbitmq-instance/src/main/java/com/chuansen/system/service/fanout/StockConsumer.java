package com.chuansen.system.service.fanout;

import com.chuansen.system.service.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 库存消费者
 */
@SuppressWarnings("all")
public class StockConsumer {
    /**
     * 定义库存队列
     */
    private static final String QUEUE_NAME = "fanout_stock_queue";
    /**
     * 定义交换机的名称
     */
    private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        System.out.println("库存消费者...");

        Connection connection = RabbitMQConnection.getConnection();

        final Channel channel = connection.createChannel();
        // 队列需要去关联交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("库存消费者获取消息:" + msg);
            }
        };
        // 开始监听消息 自动签收
        channel.basicConsume(QUEUE_NAME, true, defaultConsumer);

    }
}
