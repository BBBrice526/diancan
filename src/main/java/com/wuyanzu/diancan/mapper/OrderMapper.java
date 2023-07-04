package com.wuyanzu.diancan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyanzu.diancan.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
