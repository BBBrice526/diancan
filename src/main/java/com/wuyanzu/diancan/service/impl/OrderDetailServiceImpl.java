package com.wuyanzu.diancan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyanzu.diancan.entity.OrderDetail;
import com.wuyanzu.diancan.mapper.OrderDetailMapper;
import com.wuyanzu.diancan.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    public String setAllOdOne(Long oid){
        UpdateWrapper<OrderDetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("oid",oid)
                .eq("odstatus",0)
                .set("odstatus",1);
        return "修改订单商品成功";
    }
}
