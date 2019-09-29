package com.neusoft.biz.nonpanel.model;

import java.io.Serializable;

import com.neusoft.base.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NonPanelCpt extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer nonPanelCptId;
    private String complaintNo;// 客诉单号
    private String orderNo;// 订单号
    private String receiveTime;// 反馈时间
    private String src;// 客诉来源
    private String cs;// 客服专员
    private String componentEditing;// 部件物编
    private Integer partOrderQty;// 部件订单量
    private Integer numberOfComplaints;// 投诉数量
    private Double nonPerformingRate;// 不良率(%)
    private String badPhase;// 不良阶段
    private String rmaFailureType;// RMA故障现象类型
    private String nonRmaFailureType;// 非RMA故障现象类型
    private String faultremark;// 故障现象描述
    private String partName;// 部件名称
    private String componentName;// 元器件名称
    private String initialType;// 初审意见类型
    private String initialResponseTime;// 初审意见时间
    private String temporaryMeasureType;// 临时措施类型
    private String temporaryMeasuresremark;// 临时措施描述
    private String reserveLossOrder;// 备损订单号
    private Double reserveLossAmount;// 备损订单金额
    private String damageLossShipping;// 备损出货方式
    private String trackingNumber;// 追溯单号
    private Double repairFees;// 维修费用
    private Double compensationFee;// 赔偿费用
    private String confirmationSolution;// 确认解决方案
    private String confirmSolutionTime;// 确认解决方案时间
    private String qualityFileFeedbackTime;// 质量文件反馈时间
    private String qualitydocRecipient;// 质量文件接收人
    private String reportReceivingTime;// 8D报告接收时间
    private String reportForwardingTime;// 8D报告转发技术组时间
    private String technicalRespondedTime;// 技术组回复审核意见时间
    private String reportRespondsTime;// 8D报告回复客户时间
    private String closedRemark;// 投诉闭环措施
    private String closedTime;// 投诉闭环时间
    private String primaryToDotype;// 首要待办事项类型
    private String estimatedFinishTime;// 预计完成时间

    private String custName; // 客户名称
    private String brand; // 品牌
    private String deliveryForm; // 走货方式
    private String movement; // 机芯
    private String ourModel; // 我司型号
    private String custModel; // 买家型号
    private Integer orderQty; // 订单量
    private String soldToCountry; // 销往国家

    private String receiveTimeStart;
    private String receiveTimeEnd;
    private String closedTimeStart;
    private String closedTimeEnd;
}