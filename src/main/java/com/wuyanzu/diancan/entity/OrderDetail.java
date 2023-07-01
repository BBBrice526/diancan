package com.wuyanzu.diancan.entity;

import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("order_detail")
public class OrderDetail {
    @TableId
    private Long odid;          //商品id

    @NotNull(message = "请输入所属订单")
    private Long oid;               //商品所属订单id

    @NotNull(message = "请输入菜品编号")
    private Integer fid;            //商品内容id

    private String odname;          //商品名

    private String odimage;         //商品图片url

    private double odprice;         //商品价格

    @NotNull(message = "请输入菜品数量")
    private Integer odcount;        //商品数量

    private String taste;           //商品备注

    private Integer odstatus;       //商品状态标识，0为已下单、1为已完成
}
