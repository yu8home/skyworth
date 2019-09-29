package com.neusoft.biz.console.sys.coding.service.impl;

import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.console.sys.coding.dao.CodingMapper;
import com.neusoft.biz.console.sys.coding.model.Coding;
import com.neusoft.biz.console.sys.coding.service.CodingService;

@Service
public class CodingServiceImpl extends AbstractBaseService<CodingMapper, Coding> implements CodingService {

    @Override
    public String getCodingNextVal(String ruleCode) {
        return baseMapper.getCodingNextVal(ruleCode);
    }

}