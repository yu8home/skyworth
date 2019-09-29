package com.neusoft.biz.console.sys.role.dao;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.console.sys.role.model.Role;

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    void insertRoleAuth(Role t);
}