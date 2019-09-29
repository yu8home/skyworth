package com.neusoft.base.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import cn.hutool.core.exceptions.ExceptionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * RestController异常处理返回json，
 * 将“@ExceptionHandler、@InitBinder、@ModelAttribute”应用到所有@RequestMapping中，
 * 只能处理controller内部异常，对于未进入controller前的异常无法捕获，SpringBoot提供了ErrorController来处理所有异常
 * </pre>
 *
 * @author：yu8home
 * @date：2017年12月21日 下午3:08:52
 */
@Slf4j
@RestControllerAdvice
public class MyRestControllerAdvice {

    @ExceptionHandler
    // @ExceptionHandler(value = java.lang.NullPointerException.class)
    public R exception(Exception e) {
        log.error(ExceptionUtil.stacktraceToString(e));
        if (log.isDebugEnabled()) {
            e.printStackTrace();
        }
        return R.fail(e.getMessage());
    }

}