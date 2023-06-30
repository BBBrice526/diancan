package com.wuyanzu.diancan.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuyanzu.diancan.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
