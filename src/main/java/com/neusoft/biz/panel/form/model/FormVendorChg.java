package com.neusoft.biz.panel.form.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 供应商变更
 *
 * @author：yu8home
 * @date：2018年7月19日 下午12:57:01
 */
@Getter
@Setter
public class FormVendorChg implements Serializable {
    private static final long serialVersionUID = 1L;

    private int complaintId;
    private String vendorCode;
    private String startTime;
    private String endTime;
    private String remark;

    private String ids;
}