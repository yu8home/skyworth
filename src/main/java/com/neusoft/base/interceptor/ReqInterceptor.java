package com.neusoft.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.base.utils.ShiroUtils;

/**
 * 拦截前台请求
 *
 * @author：yu8home
 * @date：2017年12月22日 下午1:22:08
 */
@Component
public class ReqInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        request.setAttribute("path", path);
        request.setAttribute("basePath", basePath);

        request.setAttribute("user", ShiroUtils.getUser());
        request.setAttribute("sysStartupTime", GlobalConst.SYS_STARTUP_TIME);// 每次服务重启后对私有“css、js”文件增加后缀防止缓存
        request.setAttribute("language", LocaleContextHolder.getLocale().toString());
        return true;
    }

}