package com.neusoft.biz.archives.custspc.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.archives.custspc.model.CustSpc;
import com.neusoft.biz.console.sys.user.model.User;

@Mapper
@SuppressWarnings({ "rawtypes" })
public interface CustSpcMapper extends BaseMapper<CustSpc> {

    List<User> qryRelUser(Map t);

    List<User> qryUnRelUser(Map t);

    void addRelUser(Map t);

    void deleteRelUser(Map t);
}