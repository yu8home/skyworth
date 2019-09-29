package com.neusoft.base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面链接Controller
 *
 * @author：yu8home
 * @date：2017年9月4日 下午10:23:47
 */
@Controller
public class PageController {

    @RequestMapping("{a}-page")
    public String page(@PathVariable("a") String a) {
        return a;
    }

    @RequestMapping("{a}/{b}-page")
    public String page(@PathVariable("a") String a, @PathVariable("b") String b) {
        return a + "/" + b;
    }

    @RequestMapping("{a}/{b}/{c}-page")
    public String page(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") String c) {
        return a + "/" + b + "/" + c;
    }

    @RequestMapping("{a}/{b}/{c}/{d}-page")
    public String page(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") String c, @PathVariable("d") String d) {
        return a + "/" + b + "/" + c + "/" + d;
    }

    @RequestMapping("{a}/{b}/{c}/{d}/{e}-page")
    public String page(@PathVariable("a") String a, @PathVariable("b") String b, @PathVariable("c") String c, @PathVariable("d") String d, @PathVariable("e") String e) {
        return a + "/" + b + "/" + c + "/" + d + "/" + e;
    }

}