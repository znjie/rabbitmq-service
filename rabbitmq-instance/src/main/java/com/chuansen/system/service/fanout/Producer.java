package com.chuansen.system.service.fanout;

import com.chuansen.system.service.RabbitMQConnection;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SuppressWarnings("all")
public class Producer {

    /**
     * 定义交换机的名称
     */
    private static final String EXCHANGE_NAME = "fanout_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {
        //  创建Connection
        Connection connection = RabbitMQConnection.getConnection();
        // 创建Channel
        Channel channel = connection.createChannel();
        // 通道关联交换机 【参数说明：参数一：交换器的名称，参数二：交换器的类型, 常见类型有fanout, direct, topic, headers；参数三：是否独占模式；设置是否持久化, 】
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true);
        String msg = "消息投递到扇型交换机fanout场景示例消息";

        channel.confirmSelect();

        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        try {
            boolean result= channel.waitForConfirms();
            if (result){
                System.out.println("消息投递成功");
            }else {
                System.out.println("消息投递失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        channel.close();
        connection.close();
    }
}
