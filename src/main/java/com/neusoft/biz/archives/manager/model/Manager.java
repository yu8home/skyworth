package com.neusoft.biz.archives.manager.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;

    private String managerCode;// 管理员编码
    private String managerName;
    private Integer isValid;
}