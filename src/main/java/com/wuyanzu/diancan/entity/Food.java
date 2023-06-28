package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Food {
    private Integer fid;

    @Size(max = 20,message = "菜名长度0-20！")
    private String fname;

    private String fimage;

    @NotNull(message = "请介绍一下菜品")
    private String finfo;

    @DecimalMax(value = "999.990",message = "价格不超过999.99元")
    private double fprice;

    private boolean fstatus;

    @NotNull(message = "请输入菜品种类")
    private String ftype;

    private boolean noteat;

    private boolean hot;

    private boolean sweet;

    private boolean temp;
}
