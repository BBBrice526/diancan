package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {
    private Long oid;         //订单id

    private Integer ostatus;     //订单状态：0为购物车态、1为已下单、2为准备中、3为已支付、4为取消，显示、5为特殊取消，不显示

    @Max(value = 50,message = "没有那么多桌，你输错了")
    private Integer tnum;           //桌号

    private Long uid;               //下单用户id

    @NotNull(message = "价格不能为空")
    private double oprice;            //订单总价

    private String remark;              //订单备注

    private Timestamp createTime;           //下单时间
}
