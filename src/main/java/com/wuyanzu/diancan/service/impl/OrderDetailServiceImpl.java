package com.wuyanzu.diancan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyanzu.diancan.entity.OrderDetail;
import com.wuyanzu.diancan.mapper.OrderDetailMapper;
import com.wuyanzu.diancan.service.OrderDetailService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {

    public String setAllOdOne(Long oid){
        UpdateWrapper<OrderDetail> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("oid",oid)
                .eq("odstatus",0)
                .set("odstatus",1);
        return "修改订单商品成功";
    }

    public double sumPrice(Long oid){
        QueryWrapper<OrderDetail> queryWrapper = new QueryWrapper<OrderDetail>();
        queryWrapper.eq("oid",oid);
        queryWrapper.eq("odstatus",0);
        queryWrapper.select("IFNULL(sum(odprice),0) AS sumprice");
        Map<String, Object> map = getMap(queryWrapper);
        return Double.parseDouble(String.valueOf(map.get("sumprice")));
    }
}
