package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.OrderDetail;
import com.wuyanzu.diancan.entity.Orders;
import com.wuyanzu.diancan.service.FoodService;
import com.wuyanzu.diancan.service.OrderDetailService;
import com.wuyanzu.diancan.service.OrdersService;
import com.wuyanzu.diancan.utils.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/od")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private FoodService foodService;

    @ApiOperation("添加商品到订单")
    @PostMapping("/add")
    public Result add(@RequestBody Map<String, Object> data/*OrderDetail orderDetail,Integer tnum*/){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOdname((String) data.get("odname"));
        orderDetail.setFid((Integer) data.get("fid"));
        String odimage = foodService.getById(orderDetail.getFid()).getFimage();
        double fprice = foodService.getById(orderDetail.getFid()).getFprice();
        orderDetail.setTaste((String) data.get("taste"));
        orderDetail.setOdprice(fprice);
        Integer tnum = Integer.valueOf((String) data.get("tnum"));
        log.info("商品信息:{}"+tnum,orderDetail);
        Orders orders = ordersService.thereIsOrder(tnum);
        log.info(orders.toString());
        Long oid = orders.getOid();
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();              // 查询当前菜品是否在订单中，未上菜状态
        queryWrapper.eq(OrderDetail::getOid,oid);
        queryWrapper.eq(OrderDetail::getOdname,orderDetail.getOdname());
        queryWrapper.eq(OrderDetail::getOdstatus,0);
        queryWrapper.eq(OrderDetail::getTaste,orderDetail.getTaste());
        OrderDetail orderDetail1 = orderDetailService.getOne(queryWrapper,false);
        if(orderDetail1 == null){                                                           // 如果订单中已存在 则在原来基础上数量加1即可
            orderDetail.setOdcount(1);
            orderDetail.setOid(oid);
            orderDetail.setOdimage(odimage);
            orderDetailService.save(orderDetail);
        }else{                                                                      // 如果不存在 则正常添加
            Integer count = orderDetail1.getOdcount();
            orderDetail1.setOdcount(count + 1);
            orderDetail1.setOdprice(fprice * (count + 1));
            orderDetail1.setOid(oid);
            orderDetailService.updateById(orderDetail1);
        }
        double sumPrice = orderDetailService.sumPrice(oid);
        orders.setOprice(sumPrice);
        IPage<OrderDetail> orderDetailIPage = (IPage<OrderDetail>) getOd(tnum).getData();
        return Result.success(200,"商品加到订单",orderDetailIPage);
    }



    @ApiOperation("获得订单下所有商品")
    @GetMapping("/get")
    public Result getDetails(@RequestParam Long oid){
        //log.info(oid.toString());
        Page<OrderDetail> odpage = new Page<>();
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOid,oid);
        IPage<OrderDetail> iPage = orderDetailService.page(odpage,queryWrapper);
        return Result.success(200,"该订单商品有",iPage);
    }

    @ApiOperation("减少商品")
    @GetMapping("/remove")
    public Result remove(@RequestParam Long odid,Integer tnum){
        log.info(odid.toString());
        OrderDetail orderDetail = orderDetailService.getById(odid);
        Orders orders = ordersService.getById(orderDetail.getOid());
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();              // 查询当前菜品是否在订单中，未上菜状态
        queryWrapper.eq(OrderDetail::getOid,orderDetail.getOid());
        queryWrapper.eq(OrderDetail::getOdname,orderDetail.getOdname());
        queryWrapper.eq(OrderDetail::getTaste,orderDetail.getTaste());
        queryWrapper.eq(OrderDetail::getOdstatus,0);
        double fprice = foodService.getById(orderDetail.getFid()).getFprice();
        OrderDetail orderDetail1 = orderDetailService.getOne(queryWrapper);
        if(orderDetail1 == null){
            return Result.error(201,"该商品不在订单内");
        }else if(orderDetail1.getOdcount() == 1){
            orderDetailService.removeById(orderDetail1);
        }else {
            Integer count = orderDetail1.getOdcount();
            orderDetail1.setOdcount(count - 1);
            orderDetail1.setOdprice(fprice * (count - 1));
            orderDetailService.updateById(orderDetail1);
        }
        IPage<OrderDetail> orderDetailIPage = (IPage<OrderDetail>) getOd(tnum).getData();
        double sumPrice = orderDetailService.sumPrice(orders.getOid());
        orders.setOprice(sumPrice);
        return Result.success(200,"商品已减少",orderDetailIPage);
    }


    @ApiOperation("商品状态变更")
    @PostMapping("/update")
    public Result updateOd(Long odid, Integer odstatus){
        //log.info(odstatus.toString());
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOdid,odid);
        OrderDetail orderDetail = orderDetailService.getOne(queryWrapper);
        if(orderDetail == null){
            return Result.error(201,"没有该商品");
        }
        orderDetail.setOdstatus(odstatus);
        orderDetailService.updateById(orderDetail);
        return Result.success(200,"状态修改成功",odstatus);
    }

    @ApiOperation("获得订单下未完成商品")
    @GetMapping("/getodtnum")
    public Result getOdTnum(@RequestParam Integer tnum){
        Page<OrderDetail> odpage = new Page<>();
        LambdaQueryWrapper<Orders> oqueryWrapper = new LambdaQueryWrapper<>();

        oqueryWrapper.eq(Orders::getTnum,tnum);
        oqueryWrapper.ne(Orders::getOstatus,3)
                .ne(Orders::getOstatus,4)
                .ne(Orders::getOstatus,5);
        Orders orders = ordersService.getOne(oqueryWrapper);
        if(orders == null){
            return Result.error(201,"当前桌子无准备中订单");
        }
        Long oid = orders.getOid();
        log.info(oid.toString());
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOid,oid);
        queryWrapper.ne(OrderDetail::getOdstatus,0)
                .ne(OrderDetail::getOdstatus,3);
        IPage<OrderDetail> iPage = orderDetailService.page(odpage,queryWrapper);
        return Result.success(200,"该订单商品有",iPage);
    }

    @ApiOperation("获得订单下未完成商品")
    @GetMapping("/getod")
    public Result getOd(@RequestParam Integer tnum){
        //log.info(tnum.toString());
        Page<OrderDetail> odpage = new Page<>();
        LambdaQueryWrapper<Orders> oqueryWrapper = new LambdaQueryWrapper<>();

        oqueryWrapper.eq(Orders::getTnum,tnum);
        oqueryWrapper.ne(Orders::getOstatus,3)
                .ne(Orders::getOstatus,4)
                .ne(Orders::getOstatus,5);
        Orders orders = ordersService.getOne(oqueryWrapper);
        if(orders == null){
            return Result.error(201,"当前桌子无准备中订单");
        }
        Long oid = orders.getOid();
        log.info(oid.toString());
        LambdaQueryWrapper<OrderDetail> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getOid,oid);
        queryWrapper.eq(OrderDetail::getOdstatus,0);
        IPage<OrderDetail> iPage = orderDetailService.page(odpage,queryWrapper);
        return Result.success(200,"该订单商品有",iPage);
    }

    @ApiOperation("未完成商品总价")
    @GetMapping("/getodsum")
    public Result getOdSumPrice(Long oid){
        double sumPrice = orderDetailService.sumPrice(oid);
        return Result.success(200,"",sumPrice);
    }

}
