package com.neusoft.biz.panel.form.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.model.FormLog;

@Mapper
public interface FormMapper extends BaseMapper<Form> {

    List<Form> qryForListExcludeImg(Form t);

    Form getSimpleInfo(Integer complaintId);

    String getAppClosedReason(Integer complaintId);

    void insertFormLog(FormLog dt);

    List<FormLog> qryFormLog(Form t);

    void updateStatus(Form t);

    void saveFormRemark(Form t);
}