package com.neusoft.biz.archives.custspc.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustSpc implements Serializable {
    private static final long serialVersionUID = 1L;

    private String custServCode;// 客服专员账号
    private String custServName;
    private String linkEmail;// 联系人邮箱
    private Integer isValid;

    private String userCodes;
    private String roleType;// 授权类型：9002
}