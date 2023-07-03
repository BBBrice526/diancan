package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wuyanzu.diancan.entity.Order;
import com.wuyanzu.diancan.service.OrderService;
import com.wuyanzu.diancan.utils.Result;
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

    @PostMapping("/save")
    public Result newOrder(@RequestBody Order order, HttpSession session){
        log.info("订单数据：{}",order);
        //order.setUid((Long) session.getAttribute("uid"));
        orderService.save(order);
        return Result.success(200,"下单成功！",order);
    }

    @GetMapping("/tnum")
    public Result getOrderByTnum(@RequestParam Integer tnum){
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getTnum,tnum);
        queryWrapper.ne(Order::getOstatus,4);
        queryWrapper.ne(Order::getOstatus,5);
        Order order1 = orderService.getById(queryWrapper);
        return Result.success(200,"",order1);
    }

    @GetMapping("/uid")
    public Result getOrderByUid(@RequestParam Long uid){
        LambdaQueryWrapper<Order> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Order::getUid,uid);
        queryWrapper.ne(Order::getOstatus,4);
        queryWrapper.ne(Order::getOstatus,5);
        Order order1 = orderService.getById(queryWrapper);
        return Result.success(200,"",order1);
    }
}
