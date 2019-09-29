package com.neusoft.biz.panel.cs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.model.FormVendorChg;

@Mapper
@SuppressWarnings({ "rawtypes" })
public interface CsCptMapper extends BaseMapper<Form> {

    void insertVendorChg(FormVendorChg t);

    void updateVendorChg(FormVendorChg t);

    void updatePriority(Form t);

    void initial(Form t);

    void solution(Form t);

    List<Map> qryVendorChg(FormVendorChg t);

    void closedVendorFormStatus(Form t);
}