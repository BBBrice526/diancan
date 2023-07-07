package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Tables;
import com.wuyanzu.diancan.service.TableService;
import com.wuyanzu.diancan.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @ApiOperation("新增桌子")
    @PostMapping("/add")
    public Result addTable (){                               //新增桌子，预设为空桌，未分配服务员
        Tables tables = new Tables();
        tables.setTstatus(false);
        tableService.save(tables);
        return Result.success(200,"新增成功", tables);
    }

    @ApiOperation("更新桌子信息")
    @PostMapping("/update")
    public Result update(@RequestBody Tables tables){                 //更新桌子信息
        tableService.updateById(tables);
        return Result.success(200,"更新成功", tables);
    }

    @ApiOperation("查询某状态所有桌子")
    @GetMapping("/page")
    public Result page(int page,int pageSize,boolean tstatus){      //根据桌子状态分页查询，设定页大小
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Tables> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Tables::getTstatus,tstatus);
        IPage<Tables> iPage = tableService.page(pageInfo,queryWrapper);
        return Result.success(200,"",iPage);
    }

    @ApiOperation("查询所有桌子")
    @GetMapping("/alltable")
    public Result pageAll(int page,int pagesize){
        Page pageInfo = new Page(page,pagesize);
        LambdaQueryWrapper<Tables> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Tables::getTnum);
        IPage<Tables> iPage = tableService.page(pageInfo,queryWrapper);
        return Result.success(200,"",iPage);
    }


    @ApiOperation("删除桌子")
    @DeleteMapping("/delete")
    public  Result deleteTable(int tnum){
        tableService.removeById(tnum);
        return Result.success(200,"已删除",tnum);
    }
}
