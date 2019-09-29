package com.neusoft.biz.console.sys.resource.service;

import java.util.Map;

import com.neusoft.base.service.BaseService;
import com.neusoft.biz.console.sys.resource.model.Resource;

public interface ResourceService extends BaseService<Resource> {

    /**
     * 查询filterChainDefinitionMap
     *
     * @author：yu8home
     * @date：2018年6月17日 上午7:25:40
     */
    Map<String, String> getFilterChainDefinitionMap();
}