package com.neusoft.biz.panel.custcpt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.order.model.Order;
import com.neusoft.biz.panel.form.model.Form;

@Mapper
public interface CustCptMapper extends BaseMapper<Form> {

    void batchSubmit(Form t);

    int checkFormSn(Form t);

    void cloned(Integer complaintId);

    /**
     * 客户确认闭环执行重新提交时需要清空对应的时间
     */
    void clearFormTime(Form t);

    /**
     * 在建单的时候订单信息不是通过双击订单号获取的，需要校验并回填orderId
     */
    List<Order> qryOrderByNo(@Param("orderNo") String orderNo, @Param("custOrderNo") String custOrderNo);

    List<String> checkOcSn(@Param("ocSnList") List<String> ocSnList);

    List<String> checkModuleSn(@Param("moduleSnList") List<String> moduleSnList);
}