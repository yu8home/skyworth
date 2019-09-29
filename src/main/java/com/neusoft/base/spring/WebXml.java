package com.neusoft.base.spring;

import javax.servlet.DispatcherType;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;

import com.neusoft.base.filter.xss.XssFilter;

@Configuration
@SuppressWarnings({ "unchecked", "rawtypes" })
public class WebXml {

    /**
     * <pre>
     * <filter>
     * <filter-name>xssFilter</filter-name>
     * <filter-class>com.neusoft.base.filter.xss.XssFilter</filter-class>
     * </filter>
     * <filter-mapping>
     * <filter-name>xssFilter</filter-name>
     * <url-pattern>/*</url-pattern>
     * <dispatcher>REQUEST</dispatcher>
     * </filter-mapping>
     * </pre>
     */
    @Bean
    public FilterRegistrationBean xssFilterReg() {
        FilterRegistrationBean rs = new FilterRegistrationBean();
        rs.setFilter(new XssFilter());
        rs.setName("xssFilter");
        rs.addUrlPatterns("/*");
        rs.setDispatcherTypes(DispatcherType.REQUEST);
        return rs;
    }

    /**
     * <pre>
     * <filter>
     * <filter-name>shiroFilter</filter-name>
     * <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
     * <init-param>
     * <param-name>targetFilterLifecycle</param-name>
     * <param-value>true</param-value>
     * </init-param>
     * </filter>
     * <filter-mapping>
     * <filter-name>shiroFilter</filter-name>
     * <url-pattern>/*</url-pattern>
     * <dispatcher>REQUEST</dispatcher>
     * </filter-mapping>
     * </pre>
     */
    @Bean
    public FilterRegistrationBean shiroFilterReg() {
        FilterRegistrationBean rs = new FilterRegistrationBean();
        rs.setFilter(new DelegatingFilterProxy());
        rs.setName("shiroFilter");
        rs.addInitParameter("targetFilterLifecycle", "true");// shiroFilter生命周期：false由Spring管理(默认)、true由Servlet管理(保留原有的init,destroy)
        rs.addUrlPatterns("/*");
        rs.setDispatcherTypes(DispatcherType.REQUEST);
        return rs;
    }

}