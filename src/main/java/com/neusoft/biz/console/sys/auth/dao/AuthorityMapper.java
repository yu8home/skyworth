package com.neusoft.biz.console.sys.auth.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.console.sys.auth.model.Authority;
import com.neusoft.biz.console.sys.resource.model.Resource;

@Mapper
@SuppressWarnings({ "rawtypes" })
public interface AuthorityMapper extends BaseMapper<Authority> {

    void insertAuthRoleType(Authority t);

    List<Resource> qryAuthRec(Integer authId);

    List<Resource> qryUnAuthRec(Map t);

    void addAuthRec(Map t);

    void deleteAuthRec(Map t);
}