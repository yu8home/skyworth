package com.neusoft.biz.console.sys.user.controller;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.neusoft.base.annotation.SystemLog;
import com.neusoft.base.controller.R;
import com.neusoft.base.spring.I18N;
import com.neusoft.base.utils.ShiroUtils;

/**
 * 登录
 *
 * @author：yu8home
 * @String：2017年9月21日 下午5:19:58
 */
@Controller
public class LoginController {
    @Autowired
    private I18N i18n;

    @RequestMapping("/loging/{langauage}")
    public String loging(HttpServletRequest request, HttpServletResponse response, @PathVariable @Nullable String langauage) {
        if (StringUtils.isNotBlank(langauage)) {
            LocaleResolver lr = RequestContextUtils.getLocaleResolver(request);
            if ("zh".equals(langauage)) {
                lr.setLocale(request, response, Locale.CHINA);
            } else {
                lr.setLocale(request, response, Locale.ENGLISH);
            }
        }
        return "login";
    }

    @RequestMapping("/login")
    public String login() {
        ShiroUtils.logout();
        return "login";
    }

    /**
     * 与"/login"方法体内容一样是为了增加个日志
     */
    @SystemLog("注销")
    @RequestMapping("/logout")
    public String logout() {
        ShiroUtils.logout();
        return "login";
    }

    @SystemLog("登录")
    @ResponseBody
    @RequestMapping("/logon")
    public R logon(String userCode, String password) throws IOException {
        try {
            Subject subject = ShiroUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userCode, ShiroUtils.encrypt(password, userCode));
            subject.login(token);
        } catch (UnknownAccountException e) {
            return R.fail(i18n.getMessage("account.notExist"));// 账号不存在
        } catch (IncorrectCredentialsException e) {
            return R.fail(i18n.getMessage("accountOrPwd.incorrect"));// "账号或密码不正确"
        } catch (LockedAccountException e) {
            return R.fail(i18n.getMessage("invalid.account"));// "无效账号"
        } catch (AuthenticationException e) {
            return R.fail(i18n.getMessage("account.authentication.failed"));// "账户认证失败"
        }
        return R.success();
    }

}