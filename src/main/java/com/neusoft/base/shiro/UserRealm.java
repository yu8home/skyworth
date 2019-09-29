package com.neusoft.base.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.neusoft.base.comm.GlobalConst;
import com.neusoft.biz.console.sys.resource.model.Resource;
import com.neusoft.biz.console.sys.user.model.User;
import com.neusoft.biz.console.sys.user.service.UserService;

/**
 * 验证用户登录、授权
 *
 * @author：yu8home
 * @date：2017年09月26日 下午9:57:45
 */
@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userCode = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        User t = new User();
        t.setUserCode(userCode);
        User user = userService.getInfo(t);

        if (user == null) {
            throw new UnknownAccountException();
        }
        if (!password.equals(user.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        if (user.getIsValid() == 0) {
            throw new LockedAccountException();
        }
        return new SimpleAuthenticationInfo(user, password, ByteSource.Util.bytes(userCode), getName());
    }

    /**
     * 授权：只有需要验证权限时才调用，需要鉴权但缓存中无用户的授权信息时调用；在配有缓存的情况下只加载一次
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        // 角色
        authInfo.setRoles(userService.qryUserRoles(user.getUserId()));

        // 超级管理员：所有权限及角色
        if (GlobalConst.SUPER_ADMIN_USERCODE.equals(user.getUserCode())) {
            authInfo.addStringPermission("*");
        } else {
            // 权限
            Set<String> perms = new HashSet<String>();
            List<Resource> resources = userService.qryUserPerms(user.getUserId());
            resources.forEach(m -> {
                String auth = m.getAuth();
                if (StringUtils.isBlank(auth)) {
                    String[] url = m.getUrl().split("/");
                    int k = url.length;
                    auth = url[k - 2] + ":" + url[k - 1];
                }
                perms.add(auth);
            });
            authInfo.setStringPermissions(perms);
        }
        return authInfo;
    }

}