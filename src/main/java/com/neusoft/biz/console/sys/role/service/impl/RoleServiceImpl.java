package com.neusoft.biz.console.sys.role.service.impl;

import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.console.sys.role.dao.RoleMapper;
import com.neusoft.biz.console.sys.role.model.Role;
import com.neusoft.biz.console.sys.role.service.RoleService;

@Service
public class RoleServiceImpl extends AbstractBaseService<RoleMapper, Role> implements RoleService {

    @Override
    public void insert(Role t) {
        baseMapper.insert(t);
        baseMapper.insertRoleAuth(t);
    }

}