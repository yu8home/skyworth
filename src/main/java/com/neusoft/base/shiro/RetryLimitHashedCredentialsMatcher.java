package com.neusoft.base.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import com.neusoft.biz.console.sys.user.model.User;

/**
 * 重试次数限制：UserRealm-credentialsMatcher
 *
 * @author：Zhang Kaitao
 * @date：2014年2月18日 上午11:18:40
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {
    private Cache<String, AtomicInteger> passwordRetryCache;

    public void setCacheManager(CacheManager cacheManager) {
        this.passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String userCode = ((User) token.getPrincipal()).getUserCode();
        AtomicInteger retryCount = passwordRetryCache.get(userCode);

        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(userCode, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            passwordRetryCache.remove(userCode);
        }
        return matches;
    }

}