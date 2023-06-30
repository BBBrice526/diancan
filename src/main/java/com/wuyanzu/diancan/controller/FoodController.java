package com.wuyanzu.diancan.controller;

import com.wuyanzu.diancan.entity.Food;
import com.wuyanzu.diancan.service.FoodService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;

//    @PostMapping
//    public String save(@RequestParam(name = "file", required = false) MultipartFile file, @RequestBody @Valid Food food, BindingResult bindingResult){
//        if(!file.isEmpty()){
//            String path = "D/src/main/resources/static"
//        }
//        if(bindingResult.hasErrors()){
//            return bindingResult.getAllErrors().get(0).getDefaultMessage();
//        }
//        log.info("food={}",food.toString());
//        foodService.save(food);
//        return "新增菜品成功";
//    }
}
