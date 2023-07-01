package com.wuyanzu.diancan.entity;

import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("user")
public class User {
    @TableId
    private Long uid;       //用户id

    @NotBlank(message = "用户名不能为空")
    private String username;        //用户名

    private String phone;       //用户电话

    private String gender;      //性别

    private String openid;      //微信标识

    private Timestamp createTime;       //注册时间
}
