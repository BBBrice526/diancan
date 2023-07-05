package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wuyanzu.diancan.entity.User;
import com.wuyanzu.diancan.service.UserService;
import com.wuyanzu.diancan.utils.Result;
import javax.servlet.http.HttpSession;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation("登录，没有账号则自动注册")
    @PostMapping("/login")
    public Result login (@RequestBody User user, HttpSession session){   //用户授权登录，用openid识别用户
        log.info(user.toString());
        int hash = Math.abs(UUID.randomUUID().hashCode());
        String opid = user.getOpenid();
        user.setUid(Long.valueOf(hash));
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenid,opid);
        User user1 = userService.getOne(queryWrapper);
        if(user1 == null){                                  //若用户不存在则新增用户
            user1 = new User();
            user1.setUid(Long.valueOf(hash));
            user1.setOpenid(opid);
            user1.setUsername(user.getUsername());
            user1.setGender(user.getGender());
            userService.save(user1);
        }
        session.setAttribute("uid",user1.getUid());
        return Result.success(200,"登录成功",user1);
    }

    @ApiOperation("获取当前用户信息")
    @GetMapping
    public Result getUser(HttpSession session){                 //获得session内用户,不确定能用
        User user = userService.getById((Long)session.getAttribute("uid"));
        return Result.success(200,"当前用户信息",user);
    }


}
