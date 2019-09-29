package com.neusoft.biz.console.sys.role.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.biz.console.sys.role.model.Role;
import com.neusoft.biz.console.sys.role.service.RoleService;

/**
 * 角色
 *
 * @author：yu8home
 * @String：2017年12月3日 下午10:16:34
 */
@RestController
@RequestMapping("/console/sys/role")
public class RoleController extends AbstractBaseController<RoleService, Role> {
}