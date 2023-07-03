package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Employee;
import com.wuyanzu.diancan.service.EmployeeService;
import com.wuyanzu.diancan.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result login(@RequestBody Employee employee, HttpSession session){       //员工用手机号及密码登录，输入的密码进行加密
        String pw = employee.getPassword();
        pw = DigestUtils.md5DigestAsHex(pw.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getPhone,employee.getPhone());
        Employee employee1 = employeeService.getOne(queryWrapper);

        if(employee1 == null){                                          //根据手机号查询员工对象，判断该员工输入信息
            return Result.error(201,"用户不存在");
        }
        if(!employee1.getPassword().equals(pw)){
            return  Result.error(201,"密码错误");
        }
        if(!employee1.isEstatus()){
            return  Result.error(201,"账号冻结，去找管理");
        }
        session.setAttribute("eid",employee1.getEid());             //登录成功将eid放入session
        return Result.success(200,"登录成功",employee1);
    }

    @PostMapping("/logout")
    public Result logout(HttpSession session){                //员工登出，将员工id从session中去除
        session.removeAttribute("eid");
        return Result.success(200,"登出成功",null);
    }

    @PostMapping("/adding")
    public Result adding(@RequestBody @Valid Employee employee,BindingResult bindingResult){        //新增员工，判断输入对象是否合法，预设密码为123456
        log.info("员工信息：{}",employee.toString());
        if(bindingResult.hasErrors()){
            return Result.error(201,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }else {
            employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            employeeService.save(employee);
            return Result.success(200, "添加成功", null);
        }
    }

    @GetMapping("/page")
    public Result page(int page,int pageSize,boolean estatus){              //根据员工在职状态查询
        log.info("page={},pagesize={},estatus={}",page,pageSize,estatus);
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::isEstatus,estatus);
        queryWrapper.orderByDesc(Employee::getEid);
        employeeService.page(pageInfo,queryWrapper);
        return Result.success(200,"查询成功",pageInfo);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Employee employee){       //员工信息更改，无判断，密码加密后进入数据库
        log.info(employee.toString());
        if(!employee.getPassword().isEmpty()){
            String pw = DigestUtils.md5DigestAsHex(employee.getPassword().getBytes());
            employee.setPassword(pw);
        }
        employeeService.updateById(employee);
        return Result.success(200,"更新成功",null);
    }

    @PostMapping("/resetpw")
    public Result resetPassword(@RequestParam Long eid,@RequestParam String pw){        //只修改密码，根据eid查找员工，修改pw
        Employee employee = employeeService.getById(eid);
        employee.setPassword(DigestUtils.md5DigestAsHex(pw.getBytes()));
        return Result.success(200,"密码已修改",pw);
    }

    @GetMapping("/get")
    public Result getByEid(@RequestParam Long eid){             //根据eid查找员工
        log.info("根据工号查询");
        Employee employee = employeeService.getById(eid);
        if(employee != null){
            return Result.success(200,"员工信息",employee);
        }else{
            return Result.error(201,"该工号员工不存在");
        }
    }
}