package com.neusoft.biz.order.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.neusoft.base.dao.BaseMapper;
import com.neusoft.biz.order.model.Order;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    List<Order> qryExistsOrderNo(@Param("orderId") Integer orderId, @Param("orderNo") String orderNo);

    List<String> qryUsedOrder(@Param("ids") String ids);
}