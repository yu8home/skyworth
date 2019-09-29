package com.neusoft.biz.console.sys.logsystem.model;

import java.io.Serializable;

import com.neusoft.base.model.BaseModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogSystem extends BaseModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer syslogId;
    private String funcName;// 功能
    private String classMethod;// 类方法
    private String methodParam;// 方法参数
    private String ip;
    private Long runtime;// 运行时长：毫秒
    private Integer isSuccess;
    private String errMsg;// 异常描述
}