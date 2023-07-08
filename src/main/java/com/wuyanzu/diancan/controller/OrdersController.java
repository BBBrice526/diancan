package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Orders;
import com.wuyanzu.diancan.service.OrdersService;
import com.wuyanzu.diancan.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;

    @ApiOperation("订单生成")
    @PostMapping("/save")
    public Result newOrder(@RequestBody Orders orders, HttpSession session){
        log.info("订单数据：{}", orders);
        //order.setUid((Long) session.getAttribute("uid"));
        orders.setOstatus(0);
        ordersService.save(orders);
        return Result.success(200,"开始下单", orders);
    }

    @ApiOperation("根据桌号查询未订单")
    @GetMapping("/tnum")
    public Result getOrderByTnum(@RequestParam Integer tnum){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getTnum,tnum);
        queryWrapper.ne(Orders::getOstatus,3)
                .ne(Orders::getOstatus,4)
                .ne(Orders::getOstatus,5);
        Orders orders1 = ordersService.getById(queryWrapper);
        return Result.success(200,"", orders1);
    }

    @ApiOperation("根据uid查询未完成订单")
    @GetMapping("/uid")
    public Result getOrderByUid(@RequestParam Long uid){
        log.info(uid.toString());
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUid,uid);
        queryWrapper.ne(Orders::getOstatus,3);
        queryWrapper.ne(Orders::getOstatus,4);
        queryWrapper.ne(Orders::getOstatus,5);
        Orders orders1 = ordersService.getOne(queryWrapper);
        return Result.success(200,"", orders1);
    }

    @ApiOperation("查询用户所有订单")
    @GetMapping("/userorder")
    public Result getAllOrderByUid(@RequestParam Long uid){
        Page<Orders> orderPage = new Page<>();
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUid,uid);
        IPage<Orders> iPage = ordersService.page(orderPage,queryWrapper);
        return Result.success(200,"",iPage);
    }

    @ApiOperation("查询所有订单")
    @GetMapping("/orders")
    public Result getAllOrder(int page,int pageSize){
        Page<Orders> orderPage = new Page<>(page,pageSize);
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Orders::getOstatus)
                .ne(Orders::getOstatus,0);
        IPage<Orders> iPage = ordersService.page(orderPage,queryWrapper);
        return Result.success(200,"所有订单",iPage);
    }

    @ApiOperation("变更订单状态")
    @PostMapping("/status")
    public Result orderStatusUpdate(@RequestParam Long oid,@RequestParam Integer ostatus){
        log.info(String.valueOf(ostatus),oid.toString());
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getOid,oid);
        Orders orders = ordersService.getOne(queryWrapper);
        orders.setOstatus(ostatus);
        if(ostatus == 1){
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            orders.setCreateTime(timestamp);
        }
        ordersService.updateById(orders);
        return Result.success(200,"状态已变更", orders);
    }

    @ApiOperation("根据订单号查询订单")
    @GetMapping("/oid")
    public Result getOrderByOid(@RequestParam Long oid){
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getOid,oid);
        Orders orders1 = ordersService.getOne(queryWrapper);
        return Result.success(200,"该订单", orders1);
    }

//    @RequestMapping("/list")
//    public List<Orders> list(){
//        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
//        return ordersService.list(queryWrapper);
//    }


}