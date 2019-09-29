package com.neusoft.biz.archives.manager.dao;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.archives.manager.model.Manager;

@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
}