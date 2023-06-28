package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderDetail {
    private Long odid;

    @NotNull(message = "请输入所属订单")
    private Long oid;

    @NotNull(message = "请输入菜品编号")
    private Integer fid;

    private String odname;

    private String odimage;

    private double odprice;

    @NotNull(message = "请输入菜品数量")
    private Integer odcount;

    private String taste;

    private Integer odstatus;
}
