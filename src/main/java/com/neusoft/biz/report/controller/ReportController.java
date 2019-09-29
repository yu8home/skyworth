package com.neusoft.biz.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.report.service.ReportService;

/**
 * 报表中心
 *
 * @author：yu8home
 * @date：2018年7月27日 上午11:05:18
 */
@RestController
@RequestMapping("/report")
@SuppressWarnings({ "rawtypes" })
public class ReportController {
    @Autowired
    private ReportService reportService;

    @RequestMapping("/qryBadRate")
    public List qryBadRate(Form t) {
        return reportService.qryBadRate(t);
    }

    @RequestMapping("/qryCustCptTotal")
    public List qryCustCptTotal(Form t) {
        return reportService.qryCustCptTotal(t);
    }

    @RequestMapping("/qryKPIOne")
    public List qryKPIOne(Form t) {
        return reportService.qryKPIOne(t);
    }

    @RequestMapping("/qryKPITwo")
    public List qryKPITwo(Form t) {
        return reportService.qryKPITwo(t);
    }

}