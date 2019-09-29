package com.neusoft.biz.panel.cs.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.biz.panel.cs.dao.CsCptMapper;
import com.neusoft.biz.panel.cs.service.CsCptService;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.model.FormVendorChg;
import com.neusoft.biz.panel.form.service.FormService;

@Service
@SuppressWarnings({ "rawtypes" })
public class CsCptServiceImpl extends AbstractBaseService<CsCptMapper, Form> implements CsCptService {
    @Autowired
    private FormService formService;

    @Override
    public void insertVendorChg(FormVendorChg t) {
        baseMapper.insertVendorChg(t);
    }

    @Override
    public void updatePriority(Form t) {
        baseMapper.updatePriority(t);
    }

    @Override
    public void initial(Form t) {
        formService.insertFormLog(t);
        baseMapper.initial(t);
    }

    @Override
    public void solution(Form t) {
        formService.insertFormLog(t);
        baseMapper.solution(t);
    }

    @Override
    public void solutionExt(Form t) {
        baseMapper.solution(t);
    }

    @Override
    public void feedbackSupplier(FormVendorChg t) {
        String[] ids = t.getIds().split(",");
        for (int i = 0; i < ids.length; i++) {
            t.setComplaintId(Integer.parseInt(ids[i]));

            Form dt = formService.getSimpleInfo(t.getComplaintId());
            if (!t.getVendorCode().equals(dt.getVendorCode())) {
                baseMapper.insertVendorChg(t);
            } else {
                baseMapper.updateVendorChg(t);
            }
        }
    }

    @Override
    public void closedVendorFormStatus(Form t) {
        t.setLogRemark("关闭供应商流程：" + t.getLogRemark());
        formService.insertFormLog(t);
        baseMapper.closedVendorFormStatus(t);
    }

    @Override
    public List<Map> qryVendorChg(FormVendorChg t) {
        return baseMapper.qryVendorChg(t);
    }

}