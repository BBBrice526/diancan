package com.wuyanzu.diancan.entity;

import javax.validation.constraints.Max;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("orders")
public class Orders {
    @TableId
    @JsonSerialize(using = ToStringSerializer.class)
    private Long oid;         //订单id

    private Integer ostatus;     //订单状态：0为购物车态、1为已下单、2为准备中、3为已支付、4为取消，显示、5为特殊取消，不显示

    @Max(value = 50,message = "没有那么多桌，你输错了")
    private Integer tnum;           //桌号

    private Long uid;               //下单用户id

    private double oprice;            //订单总价

    private String remark;              //订单备注

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "createtime")
    private Timestamp createTime;           //下单时间
}
