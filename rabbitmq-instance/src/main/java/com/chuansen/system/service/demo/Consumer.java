package com.chuansen.system.service.demo;

import com.chuansen.system.service.RabbitMQConnection;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    private static final String QUEUE_NAME = "chuansen_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 1.创建连接
        Connection connection = RabbitMQConnection.getConnection();
        // 2.设置通道
        final Channel channel = connection.createChannel();
        // 声明队列【参数说明：参数一：队列名称，参数二：是否持久化；参数三：是否独占模式；参数四：消费者断开连接时是否删除队列；参数五：消息其他参数】
        //channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 3.创建订阅器，并接受消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msg);
                // 消费者完成 通知服务器从队列中删除该消息
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 4.监听队列
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
    }
}