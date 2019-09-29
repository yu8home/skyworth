package com.neusoft.biz.console.sys.resource.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Resource implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer resourceId;
    private String url;
    private String auth;// 特殊权限（不能为空字符串）
    private Integer isValid;
}