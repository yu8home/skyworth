package com.neusoft.biz.console.sys.coding.service;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.console.sys.coding.model.Coding;

public interface CodingService extends BaseService<Coding> {

    /**
     * 新建时获取编码
     *
     * @author：yu8home
     * @date：2018年6月28日 下午9:32:57
     */
    String getCodingNextVal(String ruleCode);
}