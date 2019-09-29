package com.neusoft.biz.console.sys.coding.dao;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.console.sys.coding.model.Coding;

@Mapper
public interface CodingMapper extends BaseMapper<Coding> {

    String getCodingNextVal(String ruleCode);
}