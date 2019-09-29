package com.neusoft.biz.console.sys.resource.service.impl;

import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;
import org.apache.shiro.web.servlet.AbstractShiroFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neusoft.biz.console.sys.resource.service.ResourceService;
import com.neusoft.biz.console.sys.resource.service.ShiroFilterService;

@Service
public class ShiroFilterServiceImpl implements ShiroFilterService {
    @Autowired
    private ShiroFilterFactoryBean shiroFilter;
    @Autowired
    private ResourceService resourceService;

    @Override
    public void resetFilterChainDefinitionMap() {
        AbstractShiroFilter abShiroFilter = null;
        try {
            abShiroFilter = (AbstractShiroFilter) shiroFilter.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ShiroFilterServiceImpl中调用shiroFilter.getObject()异常：" + e.getMessage());
        }
        PathMatchingFilterChainResolver filterChainResolver = (PathMatchingFilterChainResolver) abShiroFilter.getFilterChainResolver();
        DefaultFilterChainManager manager = (DefaultFilterChainManager) filterChainResolver.getFilterChainManager();
        Map<String, String> rsMap = resourceService.getFilterChainDefinitionMap();

        shiroFilter.setFilterChainDefinitionMap(rsMap);

        manager.getFilterChains().clear();
        rsMap.forEach((k, v) -> {
            manager.createChain(k, v);
        });
    }

}