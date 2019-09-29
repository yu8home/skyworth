package com.neusoft.biz.panel.custcpt.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.service.AbstractBaseService;
import com.neusoft.base.utils.ShiroUtils;
import com.neusoft.biz.order.model.Order;
import com.neusoft.biz.panel.custcpt.dao.CustCptMapper;
import com.neusoft.biz.panel.custcpt.service.CustCptService;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.service.FormService;

@Service
public class CustCptServiceImpl extends AbstractBaseService<CustCptMapper, Form> implements CustCptService {
    @Autowired
    private FormService formService;

    /**
     * 回填订单信息
     */
    private void setOrderInfo(Form t) {
        if (t.getOrderId() == null && (StringUtils.isNotBlank(t.getOrderNo()) || StringUtils.isNotBlank(t.getCustOrderNo()))) {
            List<Order> orderList = baseMapper.qryOrderByNo(t.getOrderNo(), t.getCustOrderNo());
            if (CollectionUtils.isNotEmpty(orderList) && orderList.size() == 1) {
                Order dt = orderList.get(0);
                t.setOrderId(dt.getOrderId());
                t.setOrderNo(null);
                t.setCustOrderNo(null);
            }
        }
    }

    @Override
    public void insert(Form t) {
        Integer status = t.getStatus();
        if (status == null) {
            t.setStatus(GlobalConst.STATUS_NEW);
        }
        t.setCreateUserId(ShiroUtils.getUserId());
        this.setOrderInfo(t);
        baseMapper.insert(t);
        formService.insertFormLog(t);
    }

    public void batchInsert(List<Form> rsList) {
        rsList.forEach(m -> {
            this.insert(m);
        });
    }

    @Override
    public void batchSubmit(Form t) {
        baseMapper.batchSubmit(t);
    }

    @Override
    public void update(Form t) {
        // 状态变更时才记录日志
        Form dt = formService.getSimpleInfo(t.getComplaintId());
        if (t.getStatus() != dt.getStatus().intValue()) {
            formService.insertFormLog(t);
        }
        t.setModUserId(ShiroUtils.getUserId());
        this.setOrderInfo(t);
        baseMapper.update(t);
    }

    @Override
    public void cloned(Integer complaintId) {
        baseMapper.cloned(complaintId);
    }

    @Override
    public void confirm(Form t) {
        formService.insertFormLog(t);
        formService.updateStatus(t);
    }

    @Override
    public void confirmClosed(Form t) {
        String[] ids = t.getIds().split(",");
        for (int i = 0; i < ids.length; i++) {
            t.setComplaintId(Integer.parseInt(ids[i]));
            this.confirm(t);
        }
        if (t.getStatus() == GlobalConst.STATUS_SUBMIT) {
            baseMapper.clearFormTime(t);
        }
    }

    @Override
    public int checkFormSn(Form t) {
        return baseMapper.checkFormSn(t);
    }

    @Override
    public List<String> checkOcSn(List<String> ocSnList) {
        return baseMapper.checkOcSn(ocSnList);
    }

    @Override
    public List<String> checkModuleSn(List<String> moduleSnList) {
        return baseMapper.checkModuleSn(moduleSnList);
    }

}