package com.neusoft.biz.report.service;

import java.util.List;

import com.neusoft.biz.panel.form.model.Form;

@SuppressWarnings({ "rawtypes" })
public interface ReportService {

    List qryBadRate(Form t);

    List qryCustCptTotal(Form t);

    List qryKPIOne(Form t);

    List qryKPITwo(Form t);
}