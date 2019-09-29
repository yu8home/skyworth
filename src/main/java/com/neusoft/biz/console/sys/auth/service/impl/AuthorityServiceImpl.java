package com.neusoft.biz.console.sys.auth.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.console.sys.auth.dao.AuthorityMapper;
import com.neusoft.biz.console.sys.auth.model.Authority;
import com.neusoft.biz.console.sys.auth.service.AuthorityService;
import com.neusoft.biz.console.sys.resource.model.Resource;

@Service
@SuppressWarnings({ "rawtypes" })
public class AuthorityServiceImpl extends AbstractBaseService<AuthorityMapper, Authority> implements AuthorityService {

    @Override
    public void insert(Authority t) {
        baseMapper.insert(t);
        baseMapper.insertAuthRoleType(t);
    }

    @Override
    public List<Resource> qryAuthRec(Integer authId) {
        return baseMapper.qryAuthRec(authId);
    }

    @Override
    public List<Resource> qryUnAuthRec(Map t) {
        return baseMapper.qryUnAuthRec(t);
    }

    @Override
    public void addAuthRec(Map t) {
        baseMapper.addAuthRec(t);
    }

    @Override
    public void deleteAuthRec(Map t) {
        baseMapper.deleteAuthRec(t);
    }

}