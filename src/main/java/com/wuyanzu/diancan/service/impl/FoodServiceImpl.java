package com.wuyanzu.diancan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyanzu.diancan.entity.Food;
import com.wuyanzu.diancan.mapper.FoodMapper;
import com.wuyanzu.diancan.service.FoodService;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {
}
