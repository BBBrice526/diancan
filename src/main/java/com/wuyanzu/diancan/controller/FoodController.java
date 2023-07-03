package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Food;
import com.wuyanzu.diancan.mapper.FoodMapper;
import com.wuyanzu.diancan.service.FoodService;
import javax.validation.Valid;

import com.wuyanzu.diancan.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodMapper foodMapper;

    @PostMapping("/save")
    public Result save(@RequestParam(name = "file") MultipartFile file, @RequestBody @Valid Food food, BindingResult bindingResult) throws IOException {
        if(!file.isEmpty()){
            String path = "C:/Users/BricePC/Desktop/diancan/src/main/resources/static/image";
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path + File.separator + filename));
            if(bindingResult.hasErrors()){
                return Result.error(201,bindingResult.getAllErrors().get(0).getDefaultMessage());
            }
            log.info("food={}",food.toString());
            food.setFimage(filepath.getAbsolutePath());
            foodService.save(food);
            return Result.success(200,"菜品新增成功",filepath.getAbsolutePath());
        }else {
            return Result.error(201,"请上传菜品图片");
        }
    }

//    @PostMapping("/test")
//    public Result uploadImage(@RequestParam MultipartFile file) throws IOException {
//        if(!file.isEmpty()){
//            String path = "C:/Users/BricePC/Desktop/diancan/src/main/resources/static/image";
//            String filename = file.getOriginalFilename();
//            File filepath = new File(path, filename);
//            if (!filepath.getParentFile().exists()) {
//                filepath.getParentFile().mkdirs();
//            }
//            file.transferTo(new File(path + File.separator + filename));
//            return Result.success(200,"菜品新增成功",filepath.getAbsolutePath());
//        }else {
//            return Result.error(201,"请上传菜品图片");
//        }
//    }

    @GetMapping("/getftype")
    public Result getFtype(){
        LambdaQueryWrapper<Food> queryWrapper = new LambdaQueryWrapper<>();
        //Page<Food> foodPage = new Page<>();
        queryWrapper.eq(Food::isFstatus,1);
        queryWrapper.groupBy(Food::getFtype);
        queryWrapper.select(Food::getFtype);
        queryWrapper.orderByDesc(Food::getFtype);
        List typeList = foodMapper.selectList(queryWrapper);
        //foodService.page(foodPage,queryWrapper);
        return Result.success(200,"",typeList);
    }

    @GetMapping("/pageforftype")
    public Result pageForFtype(){
        Page<Food> foodPage = new Page<>();
        QueryWrapper<Food> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("fstatus",1);
        queryWrapper.orderByDesc("ftype");
        foodService.page(foodPage,queryWrapper);
        if(queryWrapper.isEmptyOfNormal()){
            return Result.success(201,"没有该种类菜品",foodPage);
        }else{
            return Result.success(200,"该种类菜品",foodPage);
        }
    }

    @GetMapping("/pagebyftype")
    public Result pageByFtype(@RequestParam String ftype){
        Page<Food> foodPage = new Page<>();
        LambdaQueryWrapper<Food> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Food::isFstatus,1);
        queryWrapper.like(ftype!=null,Food::getFtype,ftype);
        queryWrapper.orderByDesc(Food::getFid);
        foodService.page(foodPage,queryWrapper);
        if(queryWrapper.isEmptyOfNormal()){
            return Result.success(201,"没有该种类菜品",foodPage);
        }else{
            return Result.success(200,"该种类菜品",foodPage);
        }
    }

    @RequestMapping("/get")
    public Result getByFid(@RequestParam Integer fid){
        Food food = foodService.getById(fid);
        return Result.success(200,"查找成功 ",food);
    }

    @PostMapping("/update")
    public Result update(@RequestParam(name = "file") MultipartFile file,@RequestBody @Valid Food food,BindingResult bindingResult) throws IOException {
        String path = "D/src/main/resources/static/image";
        String filename = file.getOriginalFilename();
        File filepath = new File(path, filename);
        if (!filepath.getParentFile().exists()) {
            filepath.getParentFile().mkdirs();
        }
        file.transferTo(new File(path + File.separator + filename));
        if(bindingResult.hasErrors()){
            return Result.error(201,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        log.info("food={}",food.toString());
        food.setFimage(filepath.getAbsolutePath());
        foodService.updateById(food);
        return Result.success(200,"更新成功",filepath.getAbsolutePath());
    }

    @PostMapping("/fstatus")
    public Result updateFstatus(@RequestParam boolean fstatus,@RequestParam Integer fid){
        Food food = foodService.getById(fid);
        food.setFstatus(fstatus);
        foodService.updateById(food);
        return Result.success(200,"状态修改成功",food.getFname());
    }
}
