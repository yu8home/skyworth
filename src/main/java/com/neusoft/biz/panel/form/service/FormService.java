package com.neusoft.biz.panel.form.service;

import java.util.List;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.model.FormLog;

public interface FormService extends BaseService<Form> {

    List<Form> qryForListExcludeImg(Form t);

    /**
     * 查询“简单”工单信息
     */
    Form getSimpleInfo(Integer complaintId);

    /**
     * 客服申请闭环原因
     */
    String getAppClosedReason(Integer complaintId);

    /**
     * 新增：工单操作日志
     */
    void insertFormLog(Form t);

    /**
     * 查询：工单操作日志
     */
    List<FormLog> qryFormLog(Form t);

    /**
     * 修改工单状态
     */
    void updateStatus(Form t);

    /**
     * 保存客服工单的备注
     */
    void saveFormRemark(Form t);
}