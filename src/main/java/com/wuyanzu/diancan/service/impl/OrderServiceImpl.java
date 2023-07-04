package com.wuyanzu.diancan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyanzu.diancan.entity.Orders;
import com.wuyanzu.diancan.mapper.OrderMapper;
import com.wuyanzu.diancan.service.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
}
