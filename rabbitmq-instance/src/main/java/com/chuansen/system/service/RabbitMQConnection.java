package com.chuansen.system.service;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQConnection {

    /**
     * 创建连接
     *
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //1.创建connectionFactory
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //2.配置Host
        connectionFactory.setHost("127.0.0.1");
        //3.设置Port
        connectionFactory.setPort(5672);
        //4.设置账户和密码
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //5.设置VirtualHost  分类
        connectionFactory.setVirtualHost("/");
        return connectionFactory.newConnection();
    }
}
