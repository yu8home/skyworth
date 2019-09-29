package com.neusoft.biz.console.sys.resource.service;

public interface ShiroFilterService {

    /**
     * 重置filterChainDefinitionMap：重新定义service而不是使用IResourceService是为了避免启动时和shiroFilter循环依赖
     *
     * @author：yu8home
     * @date：2018年6月17日 上午7:27:32
     */
    void resetFilterChainDefinitionMap();
}