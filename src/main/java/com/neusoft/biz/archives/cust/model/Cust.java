package com.neusoft.biz.archives.cust.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cust implements Serializable {
    private static final long serialVersionUID = 1L;

    private String custCode;// 客户账户
    private String custName;// 客户名称
    private String brand;// 品牌
    private String saleName;// 销售员
    private Integer warrantyPeriod;// 保修期 限（月）
    private String linkMan;// 联系人
    private String linkEmail;// 联系人邮箱
    private String country;// 国家
    private String customerMembers;// 客户成员：中文逗号分隔
    private String belongEnd;// 客户归属：1117
    private Integer isValid;

    private String custServName;// 关联客服
}