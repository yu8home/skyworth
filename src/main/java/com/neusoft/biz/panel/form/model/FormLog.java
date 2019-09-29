package com.neusoft.biz.panel.form.model;

import java.io.Serializable;

import com.neusoft.base.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

/**
 * 屏体工单-沟通历史记录
 *
 * @author：yu8home
 * @date：2018年7月18日 下午3:59:11
 */
@Getter
@Setter
public class FormLog extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer complaintId;
    private Integer status;
    private String remark;

    private String statusName;
}