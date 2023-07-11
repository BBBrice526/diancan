package com.wuyanzu.diancan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyanzu.diancan.entity.Orders;

public interface OrdersService extends IService<Orders> {
    public Orders thereIsOrder(Integer tnum);
}
