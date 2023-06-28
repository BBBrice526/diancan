package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Long uid;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "电话号码不能为空")
    private String phone;

    private String gender;

    private String openid;

    private Timestamp createTime;
}
