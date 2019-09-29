package com.neusoft.biz.console.sys.coding.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neusoft.base.controller.AbstractBaseController;
import com.neusoft.biz.console.sys.coding.model.Coding;
import com.neusoft.biz.console.sys.coding.service.CodingService;

/**
 * 编码规则
 *
 * @author：yu8home
 * @date：2018年1月16日 上午10:52:34
 */
@RestController
@RequestMapping("/console/sys/coding")
public class CodingController extends AbstractBaseController<CodingService, Coding> {

    @RequestMapping("/getCodingNextVal/{ruleCode}")
    public String getCodingNextVal(@PathVariable String ruleCode) {
        return baseService.getCodingNextVal(ruleCode);
    }

}