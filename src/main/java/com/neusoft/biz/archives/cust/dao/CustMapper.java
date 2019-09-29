package com.neusoft.biz.archives.cust.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.archives.cust.model.Cust;

@Mapper
public interface CustMapper extends BaseMapper<Cust> {

    void copyUser(@Param("oldCustCode") String oldCustCode, @Param("newCustCode") String newCustCode, @Param("pwd") String pwd);
}