package com.wuyanzu.diancan.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("employee")
public class Employee {
    @TableId
    private Long eid;       //员工工号

    @NotBlank(message = "请设置员工姓名")
    private String name;     //真实姓名

    private String password;     //登录密码

    @NotBlank(message = "请输入电话")
    private String phone;       //联络电话

    private String sex;        //性别

    private String role;        //角色

    @Size(max = 18,min = 18,message = "输入十八位身份证号")
    private String idnum;      //身份证号

    private boolean estatus;   //在职状态标识
}
