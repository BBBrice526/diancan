package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.OrderDetail;
import com.wuyanzu.diancan.service.OrderDetailService;
import com.wuyanzu.diancan.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/od")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping("/add")
    public Result add(@RequestBody OrderDetail orderDetail){
        //log.info("商品信息:{}",orderDetail);
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();              // 查询当前菜品是否在订单中，未上菜状态
        queryWrapper.eq(OrderDetail::getOid,orderDetail.getOid());
        queryWrapper.eq(OrderDetail::getOdname,orderDetail.getOdname());
        queryWrapper.eq(OrderDetail::getOdstatus,0);
        OrderDetail orderDetail1 = orderDetailService.getOne(queryWrapper);
        if(orderDetail1 != null){                                                           // 如果订单中已存在 则在原来基础上数量加1即可
            Integer count = orderDetail1.getOdcount();
            orderDetail1.setOdcount(count+1);
            orderDetailService.updateById(orderDetail1);
        }else{                                                                              // 如果不存在 则正常添加
            orderDetail.setOdcount(1);
            orderDetailService.save(orderDetail);
            orderDetail1 = orderDetail;
        }
        return Result.success(200,"商品加到订单",orderDetail1);
    }

    @GetMapping("/get")
    public Result getDetails(@RequestParam Long oid){
        Page<OrderDetail> odpage = new Page<>();
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOid,oid);
        orderDetailService.page(odpage,queryWrapper);
        return Result.success(200,"该订单商品有",odpage);
    }

    @PostMapping("/remove")
    public Result remove(@RequestBody OrderDetail orderDetail){
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();              // 查询当前菜品是否在订单中，未上菜状态
        queryWrapper.eq(OrderDetail::getOid,orderDetail.getOid());
        queryWrapper.eq(OrderDetail::getOdname,orderDetail.getOdname());
        queryWrapper.eq(OrderDetail::getOdstatus,0);
        OrderDetail orderDetail1 = orderDetailService.getOne(queryWrapper);
        if(orderDetail1 == null){
            return Result.error(201,"该商品不在订单内");
        }else if(orderDetail1.getOdcount() == 1){
            orderDetailService.removeById(orderDetail1);
        }else {
            Integer count = orderDetail1.getOdcount();
            orderDetail1.setOdcount(count - 1);
            orderDetailService.updateById(orderDetail1);
        }
        return Result.success(200,"商品已减少",orderDetail1);
    }

}
