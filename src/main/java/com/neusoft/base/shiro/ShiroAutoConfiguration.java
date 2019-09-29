package com.neusoft.base.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.neusoft.biz.console.sys.resource.service.ResourceService;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroAutoConfiguration {

    /**
     * thymeleaf里使用shiro的标签
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * 方法名：WebXml.shiroFilterRegistration
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, ResourceService resourceService, KickoutSessionControlFilter kickoutSessionControlFilter) {
        ShiroFilterFactoryBean sf = new ShiroFilterFactoryBean();
        sf.setSecurityManager(securityManager);
        sf.setLoginUrl("/login");
        sf.setSuccessUrl("/index");
        // sf.setUnauthorizedUrl("/login");

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        filters.put("kickout", kickoutSessionControlFilter);
        sf.setFilters(filters);

        sf.setFilterChainDefinitionMap(resourceService.getFilterChainDefinitionMap());
        return sf;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Bean
    public EhCacheManager cacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:shiro-ehcache.xml");
        return em;
    }

    @Bean
    public SessionManager sessionManager() {
        EnterpriseCacheSessionDAO sessionDao = new EnterpriseCacheSessionDAO();
        // sessionDao.setActiveSessionsCacheName("shiro-activeSessionCache");
        // sessionDao.setSessionIdGenerator(new JavaUuidSessionIdGenerator());

        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(sessionDao);
        sessionManager.setSessionIdUrlRewritingEnabled(false);// 去除url中的JSESSIONID
        return sessionManager;
    }

    @Bean
    public SecurityManager securityManager(UserRealm userRealm, EhCacheManager cacheManager, SessionManager sessionManager) {
        DefaultWebSecurityManager sm = new DefaultWebSecurityManager();
        sm.setRealm(userRealm);
        sm.setCacheManager(cacheManager);
        sm.setSessionManager(sessionManager);
        return sm;
    }

    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter(EhCacheManager cacheManager, SessionManager sessionManager) {
        KickoutSessionControlFilter kickoutFilter = new KickoutSessionControlFilter();
        kickoutFilter.setCacheManager(cacheManager);
        kickoutFilter.setSessionManager(sessionManager);
        kickoutFilter.setKickoutUrl("/login");
        return kickoutFilter;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * shiro生命周期处理器：保证实现了Shiro内部lifecycle函数的bean执行（aop方法级别的权限检查）
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
        proxyCreator.setProxyTargetClass(true);
        return proxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}