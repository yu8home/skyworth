package com.neusoft.biz.console.sys.dict.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataDict implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer dictId;
    private Integer dictCode;
    private String text;
    private String value;
    private Integer pid;
    private Integer orderNo;
    private Integer isValid;
    private String remark;
}