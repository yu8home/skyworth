package com.neusoft.biz.panel.form.model;

import java.io.Serializable;

import com.neusoft.base.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Form extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer complaintId;
    private String complaintNo;// 客诉单号
    private String custComplaintNo;// 客户投诉号
    private Integer orderId;// 订单ID
    private String orderNo; // 订单号：优先按order_id关联获取
    private String custOrderNo; // 客户订单号：优先按order_id关联获取
    private String cbuSn;// 整机序列号
    private String badPhase;// 不良阶段
    private String failureType;// 故障现象类型
    private String partName;// 部件名称
    private String panelModuleModel;// 屏体模组型号
    private String panelModuleSn;// 屏体模组序列号
    private String ocModel;// 玻璃型号
    private String ocSn;// 玻璃序列号
    private String failureRemark;// 故障现象描述
    private String exchangeTestRs;// 对调试验/MURA测试结果
    private String otherCheckRs;// 其它试验方法/结果
    private Integer status;// 状态
    private Integer vendorFormStatus;// 供应商工单状态：1113
    private Integer priority;// 工单优先级：1120
    private Integer isTempSave;// 是否暂存：1是、0否
    // 初审意见
    private String feedbackCsTime;// 反馈客服时间
    private String feedbackCsTime2;// 再次反馈客服时间
    private String firstInitialType;// 首次
    private String firstInitialTime;
    private String curInitialType;// 当前
    private String curInitialTime;
    private String initialRemark;// 初审意见备注
    // 解决方案
    private String solutionType;// 解决方案类型
    private String solutionDesc;// 解决方案描述
    private String spfNo;// 备损订单号
    private Double spfValueAmount;// 备损金额
    private String esShipmentDate;// 预计出货时间
    private String shipmentDate;// 出货时间
    private String listProvideTime;// 清单提供时间
    private String spfDeliveredType;// 备损出货方式
    private String trackingNo;// 追溯单号
    private Double reimAmount;// 赔偿费用
    private String reimAppConfirmTime;// 赔偿申请确认时间
    private String reimAppScanFile;// 赔偿申请扫描件
    private String firstSolutionTime;// 首次确认解决方案时间
    private String finalSolutionTime;// 最终确认解决方案时间
    // 供应商
    private String vendorCode;
    private String feedbackSupplierTime;// 反馈供应商时间
    private String closeSupplierTime;// 关闭供应商时间
    private String closeTime;// 闭环时间
    private String confirmCloseTime;// 客户确认闭环时间
    private String remark;// 备注

    private String cbuSnImg1;// 整机序列号
    private String cbuSnImg2;
    private String cbuSnImg3;
    private String badPhenImg1;// 不良阶段
    private String badPhenImg2;
    private String badPhenImg3;
    private String panelModuleSnImg1;// 屏体模组序列号
    private String panelModuleSnImg2;
    private String panelModuleSnImg3;
    private String ocSnImg1;// 玻璃序列号
    private String ocSnImg2;
    private String ocSnImg3;
    private String screenFacPackingImg1;// 屏厂包装箱贴纸
    private String screenFacPackingImg2;
    private String screenFacPackingImg3;
    private String outsidePackingImg1;// 包装箱外部状态
    private String outsidePackingImg2;
    private String outsidePackingImg3;
    private String insidePackingImg1;// 包装箱内部状态
    private String insidePackingImg2;
    private String insidePackingImg3;
    private String scrappedImg1;// 报废
    private String scrappedImg2;
    private String scrappedImg3;

    private String movementModel; // 机芯\机型
    private String movement; // 机芯
    private String ourModel; // 我司型号
    private String custModel; // 买家型号
    private Integer orderQty; // 订单量
    private String pid;
    private String screen; // 配屏
    private String orderBrand; // 品牌
    private String soldToCountry; // 销往国家
    private String custName; // 客户名称
    private String orderDate; // 下单日期
    private String paymentMethod; // 付款方式
    private String orderType; // 订单类型
    private String deliveryPeriod; // 走货期
    private String deliveryForm; // 走货方式
    private String interTradeTerms; // 国际贸易条款

    private String brand;// 品牌
    private String saleName;// 销售员
    private Integer warrantyPeriod;// 保修期限（月）
    private String linkMan;// 联系人
    private String linkEmail;// 联系人邮箱
    private String country;// 国家
    private String belongEnd;// 客户归属：1117

    private String statusName;
    private String vendorFormStatusName;
    private String priorityName;

    private String firstInitialTimeStart;
    private String firstInitialTimeEnd;
    private String feedbackCsTimeStart;
    private String feedbackCsTimeEnd;
    private String feedbackSupplierTimeStart;
    private String feedbackSupplierTimeEnd;
    private String closeSupplierTimeStart;
    private String closeSupplierTimeEnd;
    private String firstSolutionTimeStart;
    private String firstSolutionTimeEnd;
    private String confirmCloseTimeStart;
    private String confirmCloseTimeEnd;

    private String roleType;
    private String csCode;
    private String csCodeName;// 客服专员
    private String vendorName;
    private Integer isValid;
    private String logRemark;
    private String confirmRs;
    private String rmaNo;
    private Integer formMenuType;// 菜单类型：1客户、2客服vs客户、3客服vs供应商、4供应商、5客户工单历史、6客服工单历史、7供应商工单历史
}