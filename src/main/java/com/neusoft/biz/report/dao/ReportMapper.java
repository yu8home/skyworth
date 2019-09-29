package com.neusoft.biz.report.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.neusoft.biz.panel.form.model.Form;

@Mapper
@SuppressWarnings({ "rawtypes" })
public interface ReportMapper {

    List qryBadRate(Form t);

    List qryCustCptTotal(Form t);

    List qryKPIOne(Form t);

    List qryKPITwo(Form t);
}