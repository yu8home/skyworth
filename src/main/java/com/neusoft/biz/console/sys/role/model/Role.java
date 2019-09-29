package com.neusoft.biz.console.sys.role.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer roleId;
    private String roleCode;
    private String name;
    private String roleType;// 授权类型：9002
    private Integer isValid;

    private String authIds;
    private String roleTypeName;
}