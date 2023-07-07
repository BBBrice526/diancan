package com.wuyanzu.diancan.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wuyanzu.diancan.entity.Tables;
import com.wuyanzu.diancan.mapper.TableMapper;
import com.wuyanzu.diancan.service.TableService;
import org.springframework.stereotype.Service;

@Service
public class TableServiceImpl extends ServiceImpl<TableMapper, Tables> implements TableService {
}
