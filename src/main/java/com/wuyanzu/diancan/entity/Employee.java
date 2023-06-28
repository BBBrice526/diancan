package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Employee {
    @NotNull(message = "请设置员工工号")
    private Long eid;       //员工工号

    @NotNull(message = "请设置员工姓名")
    private String name;     //真实姓名

    private String password;     //登录密码

    @NotNull(message = "请输入电话")
    private String phone;       //联络电话

    private String sex;        //性别

    private String role;        //角色

    @NotNull(message = "请输入员工身份证号")
    private String idnum;      //身份证号
}
