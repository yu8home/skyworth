package com.neusoft.biz.console.sys.coding.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coding implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ruleCode;
    private String prefix;
    private Integer dateType;// 日期类型：0无、1年、2年月、3年月日、4年月日时分秒
    private Integer suffixDigit;// 后缀的数字位数
    private Integer resetValueType;// 值重置类型：0无、1跨年、2跨月
    private Integer curValue;// 当前值
    private String remark;

    private String dateTypeName;
    private String resetValueTypeName;
}