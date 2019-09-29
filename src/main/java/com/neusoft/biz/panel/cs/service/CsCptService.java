package com.neusoft.biz.panel.cs.service;

import java.util.List;
import java.util.Map;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.panel.form.model.Form;
import com.neusoft.biz.panel.form.model.FormVendorChg;

@SuppressWarnings({ "rawtypes" })
public interface CsCptService extends BaseService<Form> {

    /**
     * 供应商变更
     */
    void insertVendorChg(FormVendorChg t);

    /**
     * 批量修改优先级
     */
    void updatePriority(Form t);

    /**
     * 初审意见
     */
    void initial(Form t);

    /**
     * 解决方案
     */
    void solution(Form t);

    /**
     * 方案扩展信息：没有状态变更记录
     */
    void solutionExt(Form t);

    /**
     * 反馈供应商
     */
    void feedbackSupplier(FormVendorChg t);

    /**
     * 关闭供应商工单
     */
    void closedVendorFormStatus(Form t);

    /**
     * 工单对应的所有供应商（可能会变更）
     */
    List<Map> qryVendorChg(FormVendorChg t);
}