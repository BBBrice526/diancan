package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Order;
import com.wuyanzu.diancan.service.OrderService;
import com.wuyanzu.diancan.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation("订单生成")
    @PostMapping("/save")
    public Result newOrder(@RequestBody Order order, HttpSession session){
        log.info("订单数据：{}",order);
        //order.setUid((Long) session.getAttribute("uid"));
        order.setOstatus(0);
        orderService.save(order);
        return Result.success(200,"开始下单",order);
    }

    @ApiOperation("根据桌号查询未订单")
    @GetMapping("/tnum")
    public Result getOrderByTnum(@RequestParam Integer tnum){
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getTnum,tnum);
        queryWrapper.ne(Order::getOstatus,4);
        queryWrapper.ne(Order::getOstatus,5);
        Order order1 = orderService.getById(queryWrapper);
        return Result.success(200,"",order1);
    }

    @ApiOperation("根据uid查询未完成订单")
    @GetMapping("/uid")
    public Result getOrderByUid(@RequestParam Long uid){
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUid,uid);
        queryWrapper.ne(Order::getOstatus,4);
        queryWrapper.ne(Order::getOstatus,5);
        Order order1 = orderService.getById(queryWrapper);
        return Result.success(200,"",order1);
    }

    @ApiOperation("查询用户所有订单")
    @GetMapping("/userorder")
    public Result getAllOrderByUid(@RequestParam Long uid){
        Page<Order> orderPage = new Page<>();
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUid,uid);
        orderService.page(orderPage,queryWrapper);
        return Result.success(200,"",orderPage);
    }

    @ApiOperation("变更订单状态")
    @PostMapping("/status")
    public Result orderStatusUpdate(@RequestParam Long oid,@RequestParam Integer ostatus){
        Order order = orderService.getById(oid);
        order.setOstatus(ostatus);
        orderService.updateById(order);
        return Result.success(200,"状态已变更",order);
    }
}
