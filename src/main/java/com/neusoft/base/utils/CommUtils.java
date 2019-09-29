package com.neusoft.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.neusoft.base.comm.GlobalConst;

/**
 * 通用工具类
 *
 * @author：yu8home
 * @date：2017年2月7日 上午9:22:24
 */
@SuppressWarnings({ "rawtypes" })
public class CommUtils {

    /**
     * 1是、0否
     */
    public static String getYesNoName(Integer value) {
        return value == GlobalConst.YES ? "是" : "否";
    }

    /**
     * 是1、否0
     */
    public static Integer getYesNoValue(String text) {
        Integer rs = null;
        if (StringUtils.isNotBlank(text)) {
            if ("是".equals(text.trim())) {
                rs = GlobalConst.YES;
            } else if ("否".equals(text.trim())) {
                rs = GlobalConst.NO;
            }
        }
        return rs;
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    //////////////////////////////////////////////////// Object转换为基本类型///////////////////////////////////////////////////

    public static String parseString(Object obj) {
        return parseString(obj, "");
    }

    public static String parseString(Object obj, String defStr) {
        return obj == null ? defStr : obj.toString();
    }

    public static Long parseLong(Object obj) {
        if (obj != null)
            return Long.parseLong(obj.toString());
        return null;
    }

    public static Integer parseInt(Object obj) {
        if (obj != null)
            return Integer.parseInt(obj.toString());
        return null;
    }

    //////////////////////////////////////////////////// map中获取基本类型//////////////////////////////////////////////////

    public static String getAsString(Map map, String name) {
        return map.get(name).toString();
    }

    public static Integer getAsInt(Map map, String name) {
        Object v = map.get(name);
        if (v != null)
            return Integer.valueOf(v.toString());
        return null;
    }

    public static Double getAsDouble(Map map, String name) {
        Object v = map.get(name);
        if (v != null)
            return Double.valueOf(v.toString());
        return null;
    }

    public static Long getAsLong(Map map, String name) {
        Object v = map.get(name);
        if (v != null)
            return Long.valueOf(v.toString());
        return null;
    }

    public static Boolean getAsBoolean(Map map, String name) {
        Object v = map.get(name);
        if (v != null)
            return Boolean.valueOf(v.toString());
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static DecimalFormat df = new DecimalFormat("#.##");

    /**
     * 格式化数字为字符串：excel导出避免科学计数法
     */
    public static String formatMath(Object d) {
        if (d != null)
            return df.format(d);
        return null;
    }

    /**
     * 格式化对象为double
     */
    public static Double formatDouble(Object d) {
        if (d != null)
            return Double.parseDouble(df.format(d));
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void close(OutputStream out) {
        try {
            if (out != null) {
                out.close();
            }
        } catch (IOException e) {
            // TODO
        }
    }

    public static void close(InputStream in, OutputStream out) {
        CommUtils.close(null, in, out);
    }

    public static void close(Workbook wb, InputStream in) {
        CommUtils.close(wb, in, null);
    }

    public static void close(Workbook wb, InputStream in, OutputStream out) {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (wb != null) {
                wb.close();
            }
        } catch (IOException e) {
            // TODO
        }
    }

}