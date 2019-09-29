package com.neusoft.biz.order.model;

import java.io.Serializable;

import com.neusoft.base.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Order extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer orderId;
    private String orderNo; // 订单号
    private String prefix; // 序列号前缀
    private String month; // 月份
    private String orderDate; // 下单日期
    private String saleOrderNum; // 销单号
    private String custOrderNo; // 客户订单号
    private String orderType; // 订单类型
    private String pid;
    private String screen; // 配屏
    private Integer size; // 尺寸
    private String movement; // 机芯
    private String ourModel; // 我司型号
    private String custModel; // 买家型号
    private Integer orderQty; // 订单量
    private String brand; // 品牌
    private String soldToCountry; // 销往国家
    private String custName; // 客户名称
    private String receiver; // 接单人
    private String businessLeader; // 业务区负责人
    private String businessGroup; // 业务组
    private String paymentMethod; // 付款方式
    private String nationalStandardsCertification;// 国家标准及认证
    private Integer isCheck; // 是否验货
    private Integer isPaidOptionalDamage; // 付费自选备损
    private String freePreparationLoss; // 免费备损
    private Double standardAllowanceAmount; // 标准备损金额
    private Integer isRiskOrder; // 是否风险订单
    private String riskCauseAnalysis; // 风险原因分析
    private String constructionDate; // 建销单日期
    private String oneReviewPeriod; // 一次评审完成期
    private String transferOrderDate; // 转工单日期
    private String receiptOrderDate; // 接单走货日期
    private String deliveryPeriod; // 走货期
    private String deliveryForm; // 走货方式
    private String deliveryChangeReason; // 交期更改原因
    private Integer receiverDays; // 接单天数
    private Integer deliveryChangePeriod; // 走货期更改天数
    private Integer totalDays; // 合计天数
    private String movementModel; // 机芯\机型
    private String area; // 片区
    private String processingMethods; // 加工方式
    private String interTradeTerms; // 国际贸易条款
    private String interTradeRemark; // 国际贸易备注
    private String company; // 公司
    private String remark; // 备注

    private String cptCustSelOrder;// 客户建屏体工单时选择订单标记
    private String orderDateStart;
    private String orderDateEnd;
}