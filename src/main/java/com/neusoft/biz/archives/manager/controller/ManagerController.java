package com.neusoft.biz.archives.manager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.biz.archives.manager.model.Manager;
import com.neusoft.biz.archives.manager.service.ManagerService;

/**
 * 管理员
 *
 * @author：yu8home
 * @date：2018年6月28日 下午1:12:49
 */
@RestController
@RequestMapping("/archives/manager")
public class ManagerController extends AbstractBaseController<ManagerService, Manager> {
}