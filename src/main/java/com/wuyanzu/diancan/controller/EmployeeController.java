package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wuyanzu.diancan.entity.Employee;
import com.wuyanzu.diancan.service.EmployeeService;
import com.wuyanzu.diancan.utils.Result;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result login(@RequestBody Employee employee, HttpSession session){
        String pw = employee.getPassword();
        pw = DigestUtils.md5DigestAsHex(pw.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getPhone,employee.getPhone());
        Employee employee1 = employeeService.getOne(queryWrapper);

        if(employee1 == null){
            return Result.error(201,"用户不存在");
        }
        if(!employee1.getPassword().equals(pw)){
            return  Result.error(201,"密码错误");
        }
        if(!employee1.isEstatus()){
            return  Result.error(201,"账号冻结，去找管理");
        }
        session.setAttribute("eid",employee1.getEid());
        return Result.success(200,"登录成功",employee1);
    }

}
