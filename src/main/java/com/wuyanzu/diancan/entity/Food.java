package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Food {
    private Integer fid;        //菜品编号

    @Size(max = 20,message = "菜名长度0-20！")
    private String fname;        //菜品名称

    private String fimage;       //图片url

    @NotNull(message = "请介绍一下菜品")
    private String finfo;        //详细介绍

    @DecimalMax(value = "999.990",message = "价格不超过999.99元")
    private double fprice;       //菜品价格

    private boolean fstatus;     //在售标识

    @NotNull(message = "请输入菜品种类")
    private String ftype;       //菜品种类

    private boolean noteat;     //忌口标识

    private boolean hot;        //辣度标识

    private boolean sweet;      //甜度标识

    private boolean temp;       //温度标识
}