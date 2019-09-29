package com.neusoft.biz.panel.form.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.panel.form.dao.FormMapper;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.model.FormLog;
import com.neusoft.biz.panel.form.service.FormService;

@Service
public class FormServiceImpl extends AbstractBaseService<FormMapper, Form> implements FormService {

    @Override
    public List<Form> qryForListExcludeImg(Form t) {
        return baseMapper.qryForListExcludeImg(t);
    }

    @Override
    public Form getSimpleInfo(Integer complaintId) {
        return baseMapper.getSimpleInfo(complaintId);
    }

    @Override
    public String getAppClosedReason(Integer complaintId) {
        return baseMapper.getAppClosedReason(complaintId);
    }

    @Override
    public void insertFormLog(Form t) {
        FormLog dt = new FormLog();
        dt.setComplaintId(t.getComplaintId());
        dt.setStatus(t.getStatus());
        dt.setRemark(t.getLogRemark());
        dt.setCreateUserId(ShiroUtils.getUserId());
        baseMapper.insertFormLog(dt);
    }

    @Override
    public List<FormLog> qryFormLog(Form t) {
        return baseMapper.qryFormLog(t);
    }

    @Override
    public void updateStatus(Form t) {
        baseMapper.updateStatus(t);
    }

    @Override
    public void saveFormRemark(Form t) {
        baseMapper.saveFormRemark(t);
    }

}