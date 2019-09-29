package com.neusoft.biz.nonpanel.dao;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.nonpanel.model.NonPanelCpt;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NonPanelCptMapper extends BaseMapper<NonPanelCpt> {

    @Override
    @Delete("delete from d_non_panel_cpt where non_panel_cpt_id = #{id}")
    void delete(NonPanelCpt t);
}