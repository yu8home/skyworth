package com.neusoft.base.controller;

import java.util.HashMap;

import com.neusoft.base.comm.GlobalConst;

/**
 * Controller返回的响应
 *
 * @author：yu8home
 * @date：2017年9月3日 下午9:26:56
 */
public class R extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static R success() {
        return retrunR(GlobalConst.SUCCESS, GlobalConst.SUCCESS_CN);
    }

    public static R success(Object obj) {
        return retrunR(GlobalConst.SUCCESS, obj);
    }

    public static R fail() {
        return retrunR(GlobalConst.FAIL, GlobalConst.UNKNOWN_EXCEPTION);
    }

    public static R fail(String msg) {
        return retrunR(GlobalConst.FAIL, msg);
    }

    public static R retrunR(int code, Object obj) {
        R r = new R();
        r.put("code", code);
        r.put("data", obj);
        return r;
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

}