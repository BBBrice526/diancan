package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Employee {
    @NotNull(message = "请设置员工工号")
    private Long eid;

    @NotNull(message = "请设置员工姓名")
    private String name;

    private String password;

    @NotNull(message = "请输入电话")
    private String phone;

    private String sex;

    private String role;

    @NotNull(message = "请输入员工身份证号")
    private String idnum;
}
