package com.chuansen.system.service.fanout;

import com.chuansen.system.service.RabbitMQConnection;
import com.chuansen.system.service.util.Constant;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 扇型交换机      发布订阅
 */
@SuppressWarnings("all")
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = RabbitMQConnection.getConnection();

        Channel channel = connection.createChannel();
        channel.exchangeDeclare(Constant.FANOUT_EXCHANGE_NAME, BuiltinExchangeType.FANOUT, true);
        String msg = "消息投递到扇型交换机fanout场景示例消息";

        channel.confirmSelect();

        channel.basicPublish(Constant.FANOUT_EXCHANGE_NAME, "", null, msg.getBytes());
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
