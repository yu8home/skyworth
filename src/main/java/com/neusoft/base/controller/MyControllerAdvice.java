package com.neusoft.base.controller;

import org.springframework.web.servlet.ModelAndView;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller异常处理返回ModelAndView
 *
 * @author：yu8home
 * @date：2018年8月27日 下午4:39:57
 */
@Slf4j
// @ControllerAdvice
public class MyControllerAdvice {

    // @ExceptionHandler
    public ModelAndView exception(Exception e) {
        log.error(ExceptionUtil.stacktraceToString(e));
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        }

        ModelAndView mv = new ModelAndView("error");
        mv.addObject("errMsg", e.getMessage());
        return mv;
    }

}