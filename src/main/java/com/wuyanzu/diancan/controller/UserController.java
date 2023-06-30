package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wuyanzu.diancan.entity.User;
import com.wuyanzu.diancan.service.UserService;
import com.wuyanzu.diancan.utils.Result;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login (@RequestBody User user, HttpSession session){
        String opid = user.getOpenid();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getOpenid,opid);
        User user1 = userService.getOne(queryWrapper);
        if(user1 == null){
            user1 = new User();
            user1.setOpenid(opid);
            user1.setUsername(user.getUsername());
            user1.setGender(user.getGender());
            userService.save(user1);
        }
        session.setAttribute("user",user1.getUid());
        return Result.success(200,"登录成功",user1);
    }
}
