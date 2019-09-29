package com.neusoft.base.shiro;

import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.neusoft.biz.console.sys.user.model.User;

/**
 * 控制并发登录人数
 *
 * @author：Zhang Kaitao
 * @date：2014年2月18日 上午10:18:35
 */
public class KickoutSessionControlFilter extends AccessControlFilter {
    private String kickoutUrl;
    private boolean kickoutAfter = false; // 踢出之前登录的用户

    private SessionManager sessionManager;
    private Cache<String, Deque<Serializable>> cache;// 分布式集群环境下需要使用redis

    public void setKickoutUrl(String kickoutUrl) {
        this.kickoutUrl = kickoutUrl;
    }

    public void setKickoutAfter(boolean kickoutAfter) {
        this.kickoutAfter = kickoutAfter;
    }

    public void setSessionManager(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public void setCacheManager(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("shiro-kickout-session");
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;// 跳转到onAccessDenied方法
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if (!subject.isAuthenticated() && !subject.isRemembered()) {
            return true;// 如果没有登录，直接进行之后的流程
        }

        Session session = subject.getSession();
        Serializable sessionId = session.getId();
        String userCode = ((User) subject.getPrincipal()).getUserCode();

        // 同步锁
        Deque<Serializable> deque = null;
        synchronized (cache) {
            deque = cache.get(userCode);
            if (deque == null) {
                deque = new LinkedList<Serializable>();
                cache.put(userCode, deque);
            }
        }

        // 如果队列里没有此sessionId，且用户没有被踢出；放入队列
        if (!deque.contains(sessionId) && session.getAttribute("kickout") == null) {
            deque.push(sessionId);
        }

        while (deque.size() > 1) {
            Serializable kickoutSessionId = null;
            if (kickoutAfter) {
                kickoutSessionId = deque.removeFirst(); // 踢出后者
            } else {
                kickoutSessionId = deque.removeLast(); // 踢出前者
            }
            try {
                Session kickoutSession = sessionManager.getSession(new DefaultSessionKey(kickoutSessionId));
                if (kickoutSession != null) {
                    kickoutSession.setAttribute("kickout", true);// 设置需要踢出的标志
                }
            } catch (Exception e) {
                // TODO
            }
        }

        // 如果被踢出了，直接退出，重定向到踢出后的地址
        if (session.getAttribute("kickout") != null) {
            try {
                subject.logout();
            } catch (Exception e) {
                // TODO
            }
            saveRequest(request);
            WebUtils.issueRedirect(request, response, kickoutUrl);
            return false;
        }
        return true;
    }

}