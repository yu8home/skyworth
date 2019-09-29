package com.neusoft.base.utils;

import java.util.ResourceBundle;

/**
 * 读取properties文件到内存中
 *
 * @author：yu8home
 * @date：2017年8月17日 下午12:23:46
 */
public class PropUtils {
    public static final ResourceBundle RB = ResourceBundle.getBundle("application");

    public static String getProperty(String key) {
        return RB.getString(key);
    }

}