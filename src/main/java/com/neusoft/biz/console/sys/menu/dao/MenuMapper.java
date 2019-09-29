package com.neusoft.biz.console.sys.menu.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.console.sys.menu.model.Menu;

@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<Menu> qryMenuByUserId(Integer userId);

    List<Menu> qryMenuTree(Menu t);

    List<Menu> qryMenuAuthTree(String roleType);
}