package com.chuansen.system.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuansen.system.entity.Order;
import com.chuansen.system.mapper.OrderMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>  implements OrderService{
}
