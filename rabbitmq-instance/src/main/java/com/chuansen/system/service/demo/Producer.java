package com.chuansen.system.service.demo;
import com.chuansen.system.service.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 * 确保消息发送成功两种方式
 * 方式一：直接返回结果 channel.confirmSelect();   channel.waitForConfirms();
 * 方式二：事务性返回并回滚  channel.txSelect();    channel.txCommit();提交  判断channel不等于null，就进行channel.txRollback();进行回滚
 */
public class Producer {
    private static final String QUEUE_NAME = "chuansen_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //1.连接mq
        Connection connection = RabbitMQConnection.getConnection();
        //2.创建通道  设置channel
        Channel channel = connection.createChannel();
        //3.发送消息
        String msg = "6666";

        //方式一：返回消息确认机制时需要调该方法进行选择
        channel.confirmSelect();

        // 发送内容【参数说明：参数一：交换机名称；参数二：队列名称，参数三：消息的其他属性-routing headers，此属性为MessageProperties.PERSISTENT_TEXT_PLAIN用于设置纯文本消息存储到硬盘；参数四：消息主体】
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        try {
            //消息同步确认机制，可能会阻塞
            boolean result=channel.waitForConfirms();
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
