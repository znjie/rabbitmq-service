package com.chuansen.system.service.util;

public final class Constant {
    //demo示例
    public static final String DEMO_QUEUE_NAME = "chuansen_queue";

    //定义路由模式交换机
    public static final String DIRECT_EXCHANGE_NAME = "direct_exchange";
    //定义库存队列
    public static final String DIRECT_STOCK_QUEUE_NAME = "direct_stock_queue";
    //定义库存路由key
    public static final String DIRECT_STOCK_KRY = "stock";
    //定义订单队列
    public static final String DIRECT_ORDER_QUEUE_NAME = "direct_order_queue";
    //定义订单路由key
    public static final String DIRECT_ORDER_KRY = "order";


    //定义扇型模式交换机
    public static final String FANOUT_EXCHANGE_NAME = "fanout_exchange";
    //定义库存队列
    public static final String FANOUT_STOCK_QUEUE_NAME = "fanout_stock_queue";
    //定义订单队列
    public static final String FANOUT_ORDER_QUEUE_NAME = "fanout_order_queue";


    //定义主题模式交换机
    public static final String TOPIC_EXCHANGE_NAME = "topic_exchange";
    //定义库存队列
    public static final String TOPIC_STOCK_QUEUE_NAME = "topic_stock_queue";
    //定义订单队列
    public static final String TOPIC_ORDER_QUEUE_NAME = "topic_order_queue";

}
