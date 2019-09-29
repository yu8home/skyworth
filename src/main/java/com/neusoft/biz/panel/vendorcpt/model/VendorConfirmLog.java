package com.neusoft.biz.panel.vendorcpt.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VendorConfirmLog implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer complaintId;
    private String vendorCode;
    private String confirmRs;
    private String rmaNo;
    private String remark;
    private String createTime;

    private Integer status;
}