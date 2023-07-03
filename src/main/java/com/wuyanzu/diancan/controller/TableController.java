package com.wuyanzu.diancan.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wuyanzu.diancan.entity.Table;
import com.wuyanzu.diancan.service.TableService;
import com.wuyanzu.diancan.utils.Result;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/table")
public class TableController {
    @Autowired
    private TableService tableService;

    @ApiOperation("新增桌子")
    @PostMapping("/add")
    public Result addTable (){                               //新增桌子，预设为空桌，未分配服务员
        Table table= new Table();
        table.setTstatus(false);
        tableService.save(table);
        return Result.success(200,"新增成功",table);
    }

    @ApiOperation("更新桌子信息")
    @PostMapping("/update")
    public Result update(@RequestBody Table table){                 //更新桌子信息
        tableService.updateById(table);
        return Result.success(200,"更新成功",table);
    }

    @ApiOperation("查询某状态所有桌子")
    @GetMapping("/page")
    public Result page(int page,int pageSize,boolean tstatus){      //根据桌子状态分页查询，设定页大小
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Table> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Table::getTstatus,tstatus);
        tableService.page(pageInfo,queryWrapper);
        return Result.success(200,"",pageInfo);
    }
}
