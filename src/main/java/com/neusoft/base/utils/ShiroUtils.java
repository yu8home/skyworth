package com.neusoft.base.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.alibaba.druid.filter.config.ConfigTools;
import com.neusoft.biz.console.sys.user.model.User;

/**
 * Shiro
 *
 * @author：yu8home
 * @date：2017年11月24日 下午3:00:18
 */
public class ShiroUtils {

    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    public static User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }

    public static Integer getUserId() {
        return getUser().getUserId();
    }

    public static boolean isLogin() {
        return SecurityUtils.getSubject().getPrincipal() != null;
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    public static Session getSession() {
        return SecurityUtils.getSubject().getSession();
    }

    public static Object getSessionAttribute(Object key) {
        return getSession().getAttribute(key);
    }

    public static void setSessionAttribute(Object key, Object value) {
        getSession().setAttribute(key, value);
    }

    public static void removeSessionAttribute(Object key) {
        getSession().removeAttribute(key);
    }

    /**
     * 加密
     */
    public static String encrypt(String pwd) {
        return new Md5Hash(pwd).toHex();
    }

    /**
     * 盐值加密
     */
    public static String encrypt(String pwd, String userCode) {
        return new Md5Hash(pwd, userCode).toHex();
    }

    public static void main(String[] args) throws Exception {
        System.out.println(ShiroUtils.encrypt("1"));
        System.out.println(ShiroUtils.encrypt("1", "admin"));

        // druid
        String password = "1";
        String[] arr = ConfigTools.genKeyPair(512);
        System.out.println("publicKey:" + arr[1]);
        System.out.println("password:" + ConfigTools.encrypt(arr[0], password));
    }

}