package com.neusoft.biz.panel.custcpt.service;

import java.util.List;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.panel.form.model.Form;

public interface CustCptService extends BaseService<Form> {

    void batchInsert(List<Form> rsList);

    void batchSubmit(Form t);

    /**
     * 克隆
     */
    void cloned(Integer complaintId);

    /**
     * 确认：初审意见、解决方案
     */
    void confirm(Form t);

    /**
     * 确认：闭环、初审意见、To be Closed-2
     */
    void confirmClosed(Form t);

    /**
     * 校验“屏体模组序列号、玻璃序列号”唯一
     */
    int checkFormSn(Form t);

    /**
     * 校验“屏体模组序列号”唯一
     */
    List<String> checkOcSn(List<String> ocSnList);

    /**
     * 校验“玻璃序列号”唯一
     */
    List<String> checkModuleSn(List<String> moduleSnList);
}