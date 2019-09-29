package com.neusoft.biz.report.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.report.dao.ReportMapper;
import com.neusoft.biz.report.service.ReportService;

@Service
@SuppressWarnings({ "rawtypes" })
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List qryBadRate(Form t) {
        return reportMapper.qryBadRate(t);
    }

    @Override
    public List qryCustCptTotal(Form t) {
        return reportMapper.qryCustCptTotal(t);
    }

    @Override
    public List qryKPIOne(Form t) {
        return reportMapper.qryKPIOne(t);
    }

    @Override
    public List qryKPITwo(Form t) {
        return reportMapper.qryKPITwo(t);
    }

}