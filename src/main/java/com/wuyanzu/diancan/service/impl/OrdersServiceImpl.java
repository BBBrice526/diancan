package com.wuyanzu.diancan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyanzu.diancan.entity.Orders;
import com.wuyanzu.diancan.mapper.OrdersMapper;
import com.wuyanzu.diancan.service.OrdersService;
import com.wuyanzu.diancan.utils.Result;
import org.springframework.stereotype.Service;


@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
    public Orders thereIsOrder(Integer tnum){
        LambdaQueryWrapper<Orders> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getTnum,tnum);
        queryWrapper.ne(Orders::getOstatus,3);
        queryWrapper.ne(Orders::getOstatus,4);
        queryWrapper.ne(Orders::getOstatus,5);
        Orders orders = getOne(queryWrapper);
        if(orders == null){
            Orders orders1 = new Orders();
            orders1.setTnum(tnum);
            orders1.setOstatus(0);
            save(orders1);
            return orders1;
        }
        return orders;
    }
}


