package com.wuyanzu.diancan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wuyanzu.diancan.entity.OrderDetail;

public interface OrderDetailService extends IService<OrderDetail> {
    public String setAllOdOne(Long oid);
    public double sumPrice(Long oid);
}
