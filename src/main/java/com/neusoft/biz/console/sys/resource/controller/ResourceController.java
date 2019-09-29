package com.neusoft.biz.console.sys.resource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.biz.console.sys.resource.model.Resource;
import com.neusoft.biz.console.sys.resource.service.ResourceService;
import com.neusoft.biz.console.sys.resource.service.ShiroFilterService;

/**
 * 资源
 *
 * @author：yu8home
 * @date：2018年1月15日 上午10:44:30
 */
@RestController
@RequestMapping("/console/sys/resource")
public class ResourceController extends AbstractBaseController<ResourceService, Resource> {
    @Autowired
    private ShiroFilterService shiroFilterService;

    @RequestMapping("/reloadResource")
    public void reloadResource() {
        shiroFilterService.resetFilterChainDefinitionMap();
    }

}