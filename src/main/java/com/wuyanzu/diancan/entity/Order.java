package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Order {
    private Long oid;

    private Integer ostatus;

    @Max(value = 50,message = "没有那么多桌，你输错了")
    private Integer tnum;

    private Long uid;

    @NotNull(message = "价格不能为空")
    private double oprice;

    private String remark;

    private Timestamp createTime;
}
