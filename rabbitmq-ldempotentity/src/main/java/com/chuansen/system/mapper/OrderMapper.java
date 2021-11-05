package com.chuansen.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuansen.system.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
