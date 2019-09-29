package com.neusoft.base.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class CookieUtil {

    public static void addCookie(HttpServletResponse response, String key, String value, int maxAge) throws Exception {
        Cookie cookie = new Cookie(key, value);
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public static boolean clearCookie(HttpServletRequest request, HttpServletResponse response, String name) throws Exception {
        boolean flag = false;
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);
                flag = true;
            }
        }
        return flag;
    }

    public static String getCookieByName(HttpServletRequest request, String name) throws Exception {
        String retStr = null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                String cname = cookie.getName();
                if (StringUtils.isNotBlank(cname) && cname.equals(name)) {
                    retStr = cookie.getValue();
                }
            }
        }
        return retStr;
    }

}