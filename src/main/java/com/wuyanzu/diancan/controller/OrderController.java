package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Orders;
import com.wuyanzu.diancan.service.OrderService;
import com.wuyanzu.diancan.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("订单生成")
    @PostMapping("/save")
    public Result newOrder(@RequestBody Orders orders, HttpSession session){
        log.info("订单数据：{}", orders);
        //order.setUid((Long) session.getAttribute("uid"));
        orders.setOstatus(0);
        orderService.save(orders);
        return Result.success(200,"开始下单", orders);
    }

    @ApiOperation("根据桌号查询未订单")
    @GetMapping("/tnum")
    public Result getOrderByTnum(@RequestParam Integer tnum){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getTnum,tnum);
        queryWrapper.ne(Orders::getOstatus,4);
        queryWrapper.ne(Orders::getOstatus,5);
        Orders orders1 = orderService.getById(queryWrapper);
        return Result.success(200,"", orders1);
    }

    @ApiOperation("根据uid查询未完成订单")
    @GetMapping("/uid")
    public Result getOrderByUid(@RequestParam Long uid){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUid,uid);
        queryWrapper.ne(Orders::getOstatus,4);
        queryWrapper.ne(Orders::getOstatus,5);
        Orders orders1 = orderService.getById(queryWrapper);
        return Result.success(200,"", orders1);
    }

    @ApiOperation("查询用户所有订单")
    @GetMapping("/userorder")
    public Result getAllOrderByUid(@RequestParam Long uid){
        Page<Orders> orderPage = new Page<>();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUid,uid);
        orderService.page(orderPage,queryWrapper);
        return Result.success(200,"",orderPage);
    }

    @ApiOperation("查询所有订单")
    @GetMapping("/orders")
    public Result getAllOrder(int page,int pageSize){
        Page<Orders> orderPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Orders::getOstatus)
                .ne(Orders::getOstatus,0);
        orderService.page(orderPage,queryWrapper);
        return Result.success(200,"所有订单",orderPage);
    }

    @ApiOperation("变更订单状态")
    @PostMapping("/status")
    public Result orderStatusUpdate(@RequestBody Orders orders){
        if(orders.getOstatus() == 1){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            orders.setCreateTime(timestamp);
        }
        orderService.updateById(orders);
        return Result.success(200,"状态已变更", orders);
    }
}
