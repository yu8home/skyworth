package com.neusoft.biz.console.sys.auth.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer authId;
    private String authName;
    private Integer menuId;
    private Integer orderNo;
    private Integer isValid;

    private String roleTypes;// 授权类型：9002
    private String roleTypesName;
}