package com.chuansen.system.service.demo;
import com.chuansen.system.service.RabbitMQConnection;
import com.chuansen.system.service.util.Constant;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 生产者
 */
@SuppressWarnings("all")
public class Producer {

    public static void main(String[] args) throws IOException, TimeoutException {
        //  run_1();
        run_2();
    }

    /**
     * 方式一: 通过发送方确认机制实现  channel.confirmSelect();   channel.waitForConfirms();
     * @throws IOException
     * @throws TimeoutException
     */
    public static void run_1() throws IOException, TimeoutException {
        //1.连接mq
        Connection connection = RabbitMQConnection.getConnection();
        //2.创建通道  设置channel
        Channel channel = connection.createChannel();
        //3.发送消息
        String msg = "正在测试发送消息";

        //开启confirm模式
        channel.confirmSelect();

        // 发送内容【参数说明：参数一：交换机名称；参数二：队列名称，参数三：消息的其他属性-routing headers，此属性为MessageProperties.PERSISTENT_TEXT_PLAIN用于设置纯文本消息存储到硬盘；参数四：消息主体】
        channel.basicPublish("", Constant.DEMO_QUEUE_NAME, null, msg.getBytes());
        try {
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

    /**
     * 方式二: 通过事务机制实现 (事务性返回并回滚  channel.txSelect();    channel.txCommit();提交  判断channel不等于null，就进行channel.txRollback();进行回滚)
     * @throws IOException
     * @throws TimeoutException
     */
    public static void run_2() throws IOException, TimeoutException {
        //1.连接mq
        Connection connection = RabbitMQConnection.getConnection();
        //2.创建通道  设置channel
        Channel channel = connection.createChannel();

        String msg = "通过事务机制实现生产着发送消息[%d]";

        //channel开启事务
        channel.txSelect();

        channel.basicPublish("", Constant.DEMO_QUEUE_NAME,  null, String.format(msg,1).getBytes());
        channel.basicPublish("", Constant.DEMO_QUEUE_NAME,  null, String.format(msg,2).getBytes());
        channel.basicPublish("", Constant.DEMO_QUEUE_NAME,  null, String.format(msg,3).getBytes());

        //消息回滚
        channel.txRollback();

        channel.basicPublish("", Constant.DEMO_QUEUE_NAME,   null, String.format(msg,4).getBytes());

        //提交事务
        channel.txCommit();

        channel.close();
        connection.close();
    }
}
