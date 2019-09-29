package com.neusoft.biz.console.sys.logsystem.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.biz.console.sys.logsystem.model.LogSystem;
import com.neusoft.biz.console.sys.logsystem.service.LogSystemService;

/**
 * 操作日志
 *
 * @author：yu8home
 * @date：2018年2月28日 上午9:09:59
 */
@RestController
@RequestMapping("/console/sys/logsystem")
public class LogSystemController extends AbstractBaseController<LogSystemService, LogSystem> {
}