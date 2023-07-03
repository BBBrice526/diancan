package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wuyanzu.diancan.entity.User;
import com.wuyanzu.diancan.service.UserService;
import com.wuyanzu.diancan.utils.Result;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login (@RequestBody User user, HttpSession session){   //用户授权登录，用openid识别用户
        String opid = user.getOpenid();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenid,opid);
        User user1 = userService.getOne(queryWrapper);
        if(user1 == null){                                  //若用户不存在则新增用户
            user1 = new User();
            user1.setOpenid(opid);
            user1.setUsername(user.getUsername());
            user1.setGender(user.getGender());
            userService.save(user1);
        }
        session.setAttribute("uid",user1.getUid());
        return Result.success(200,"登录成功",user1);
    }

    @GetMapping
    public Result getUser(HttpSession session){                 //获得session内用户,不确定能用
        User user = userService.getById((Long)session.getAttribute("uid"));
        return Result.success(200,"当前用户信息",user);
    }


}
