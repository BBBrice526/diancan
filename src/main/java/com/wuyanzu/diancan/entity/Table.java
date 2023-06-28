package com.wuyanzu.diancan.entity;

import jakarta.validation.constraints.Max;
import lombok.Data;

@Data
public class Table {
    @Max(value = 50,message = "五十桌也太多了吧，不能超过五十桌")
    private Integer tnum;

    private Boolean tstatus;

    private Long eid;
}
