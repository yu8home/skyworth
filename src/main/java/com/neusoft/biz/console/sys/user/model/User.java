package com.neusoft.biz.console.sys.user.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer userId;
    private String userCode;// 用户编码
    private String name;// 用户名称
    private String password;// 密码：默认1
    private String roleType;// 授权类型：9002
    private Integer isValid;

    private String roleTypeName;
    private String roleNames;

    private String brand;// 品牌
    private String saleName;// 销售员
    private Integer warrantyPeriod;// 保修期限（月）
    private String linkMan;// 联系人
    private String linkEmail;// 联系人邮箱
    private String country;// 国家
}