package com.neusoft.base.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseModel {
    private String ids;
    private Integer id;
    // 创建人
    private Integer createUserId;
    private String createUserCode;
    private String createUserName;
    private String createTime;
    // 修改人
    private Integer modUserId;
    private String modUserCode;
    private String modUserName;
    private String modTime;
}