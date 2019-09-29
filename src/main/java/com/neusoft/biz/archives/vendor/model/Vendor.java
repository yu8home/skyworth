package com.neusoft.biz.archives.vendor.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vendor implements Serializable {
    private static final long serialVersionUID = 1L;

    private String vendorCode;// 供应商
    private String vendorName;
    private String brand;// 品牌
    private String linkMan;// 联系人
    private String linkEmail;// 联系人邮箱
    private Integer isValid;
}