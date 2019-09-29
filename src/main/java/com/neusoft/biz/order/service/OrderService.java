package com.neusoft.biz.order.service;

import java.util.List;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.order.model.Order;

public interface OrderService extends BaseService<Order> {

    /**
     * 新建、编辑：查询订单号是否已存在
     */
    List<Order> qryExistsOrderNo(Integer orderId, String orderNo);

    /**
     * 查询已生成工单的订单号
     */
    List<String> qryUsedOrder(String ids);
}