package com.neusoft.base.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 带有ModelMap的页面链接
 *
 * @author：yu8home
 * @date：2018年7月1日 下午10:07:28
 */
@Controller
public class PageModelController {

    @RequestMapping("{a}-pageModel")
    public String pageModel(@PathVariable("a") String a, @RequestParam Map<String, Object> params, ModelMap m) {
        m.addAllAttributes(params);
        return a;
    }

    @RequestMapping("{a}/{b}-pageModel")
    public String pageModel(@PathVariable("a") String a, @PathVariable("b") String b, @RequestParam Map<String, Object> params, ModelMap m) {
        m.addAllAttributes(params);
        return a + "/" + b;
    }

    @RequestMapping("{a}/{b}/{c}-pageModel")
    public String pageModel(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") String c, @RequestParam Map<String, Object> params, ModelMap m) {
        m.addAllAttributes(params);
        return a + "/" + b + "/" + c;
    }

    @RequestMapping("{a}/{b}/{c}/{d}-pageModel")
    public String pageModel(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") String c, @PathVariable("d") String d, @RequestParam Map<String, Object> params, ModelMap m) {
        m.addAllAttributes(params);
        return a + "/" + b + "/" + c + "/" + d;
    }

    @RequestMapping("{a}/{b}/{c}/{d}/{e}-pageModel")
    public String pageModel(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") String c, @PathVariable("d") String d, @PathVariable("e") String e, @RequestParam Map<String, Object> params, ModelMap m) {
        m.addAllAttributes(params);
        return a + "/" + b + "/" + c + "/" + d + "/" + e;
    }

}