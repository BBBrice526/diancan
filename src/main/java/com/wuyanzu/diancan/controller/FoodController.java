package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Food;
import com.wuyanzu.diancan.mapper.FoodMapper;
import com.wuyanzu.diancan.service.FoodService;
import javax.validation.Valid;

import com.wuyanzu.diancan.utils.Result;
import io.swagger.annotations.ApiOperation;
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

    private String path = "C:/Users/BricePC/Desktop/diancan/src/main/resources/static/image";


    @ApiOperation("新增菜品")
    @PostMapping("/save")
    public Result save(@RequestBody @Valid Food food, BindingResult bindingResult) throws IOException {
        log.info("food={}",food.toString());
        if(bindingResult.hasErrors()){
                return Result.error(201,bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        foodService.save(food);
        return Result.success(200,"菜品新增成功",food);
    }



    @PostMapping("/upload")
    public Result uploadImage(@RequestParam MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            String filename = file.getOriginalFilename();
            File filepath = new File(path, filename);
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            file.transferTo(new File(path + File.separator + filename));
            return Result.success(200,"菜品新增成功",filepath.getAbsolutePath());
        }else {
            return Result.error(201,"上传图片失败");
        }
    }

    @ApiOperation("得到所有菜品种类")
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

    @ApiOperation("根据菜品种类排序后得到所有菜品信息")
    @GetMapping("/pageforftype")
    public Result pageForFtype(int page,int pageSize){
        Page<Food> foodPage = new Page<>(page,pageSize);
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

    @ApiOperation("根据菜品种类获得该种类菜品信息")
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

    @ApiOperation("根据fid查找")
    @RequestMapping("/get")
    public Result getByFid(@RequestParam Integer fid){
        Food food = foodService.getById(fid);
        return Result.success(200,"查找成功 ",food);
    }

    @ApiOperation("更新菜品信息")
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

    @ApiOperation("只修改菜品状态")
    @PostMapping("/fstatus")
    public Result updateFstatus(@RequestParam boolean fstatus,@RequestParam Integer fid){
        Food food = foodService.getById(fid);
        food.setFstatus(fstatus);
        foodService.updateById(food);
        return Result.success(200,"状态修改成功",food.getFname());
    }
}
