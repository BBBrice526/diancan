package com.wuyanzu.diancan.entity;

import javax.validation.constraints.Max;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("table")
public class Table {
    @TableId
    @Max(value = 50,message = "五十桌也太多了吧，不能超过五十桌")
    private Integer tnum;    //桌号

    private Boolean tstatus;        //空桌标识

    private Long eid;       //负责员工id
}
