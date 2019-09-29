package com.neusoft.base.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

/**
 * 接口请求校验：json
 *
 * @author：yu8home
 * @date：2016年12月29日 下午1:39:27
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class InterfaceReqCheck {

    public static String parseString(Map json, String key, Map errMap) {
        return parseString(json, key, null, errMap, true);
    }

    /**
     * 转换String
     *
     * @param json
     *            需要转换的json
     * @param key
     *            对应的key
     * @param errMap
     *            校验错误的map
     * @param flag
     *            是否允许为空
     * @return
     * @author：yu8home
     * @date：2016年12月29日 下午1:47:17
     */
    public static String parseString(Map json, String key, String keyName, Map errMap, boolean flag) {
        String rs = null;
        Object keyObj = json.get(key);
        if (keyObj == null || (keyObj != null && "".equals(keyObj.toString()))) {
            if (!flag) {
                errMap.put(keyName, " 不能为空");
            }
        } else {
            rs = keyObj.toString();
        }
        return rs;
    }

    /**
     * 时间格式的字符串转换
     *
     * @param json
     *            需要转换的json
     * @param key
     *            对应的key
     * @param format
     *            日期格式，如：yyyy-MM-dd HH:mm:ss
     * @param errMap
     *            校验错误的map
     * @param flag
     *            是否允许为空
     * @return
     * @author：yu8home
     * @date：2017年1月11日 上午11:48:49
     */
    public static Calendar parseCalendar(Map json, String key, String keyName, String format, Map errMap, boolean flag) {
        Calendar cl = null;
        Object keyObj = json.get(key);
        if (keyObj == null || (keyObj != null && "".equals(keyObj.toString()))) {
            if (!flag) {
                errMap.put(keyName, " 不能为空");
            }
        } else {
            try {
                String keyStr = keyObj.toString();
                if (keyStr.length() != format.length()) {
                    errMap.put(keyName, " 日期格式错误");
                } else {
                    SimpleDateFormat df = new SimpleDateFormat(format);
                    cl = Calendar.getInstance();
                    cl.setTime(df.parse(keyStr));
                }
            } catch (Exception e) {
                errMap.put(keyName, " 日期格式错误");
            }
        }
        return cl;
    }

    public static Long parseLong(Map json, String key, Map errMap) {
        return parseLong(json, key, null, errMap, true);
    }

    /**
     * 转换Long
     *
     * @param json
     *            需要转换的json
     * @param key
     *            对应的key
     * @param errMap
     *            校验错误的map
     * @param flag
     *            是否允许为空
     * @return
     * @author：yu8home
     * @date：2016年12月29日 下午1:47:17
     */
    public static Long parseLong(Map json, String key, String keyName, Map errMap, boolean flag) {
        Long rs = null;
        Object keyObj = json.get(key);
        if (keyObj == null || (keyObj != null && "".equals(keyObj.toString()))) {
            if (!flag) {
                errMap.put(keyName, " 不能为空");
            }
        } else {
            try {
                rs = Long.parseLong(keyObj.toString());
            } catch (Exception e) {
                errMap.put(keyName, " 不是整数");
            }
        }
        return rs;
    }

    public static Integer parseInteger(Map json, String key, Map errMap) {
        return parseInteger(json, key, null, errMap, true);
    }

    /**
     * 转换Integer
     *
     * @param json
     *            需要转换的json
     * @param key
     *            对应的key
     * @param errMap
     *            校验错误的map
     * @param flag
     *            是否允许为空
     * @return
     * @author：yu8home
     * @date：2016年12月29日 下午1:47:17
     */
    public static Integer parseInteger(Map json, String key, String keyName, Map errMap, boolean flag) {
        Integer rs = null;
        Object keyObj = json.get(key);
        if (keyObj == null || (keyObj != null && "".equals(keyObj.toString()))) {
            if (!flag) {
                errMap.put(keyName, " 不能为空");
            }
        } else {
            try {
                rs = Integer.parseInt(keyObj.toString());
            } catch (Exception e) {
                errMap.put(keyName, " 不是整数");
            }
        }
        return rs;
    }

    public static Double parseDouble(Map json, String key, Map errMap) {
        return parseDouble(json, key, null, errMap, true);
    }

    /**
     * 转换Double
     *
     * @param json
     *            需要转换的json
     * @param key
     *            对应的key
     * @param errMap
     *            校验错误的map
     * @param flag
     *            是否允许为空
     * @return
     * @author：yu8home
     * @date：2016年12月29日 下午1:47:17
     */
    public static Double parseDouble(Map json, String key, String keyName, Map errMap, boolean flag) {
        Double rs = null;
        Object keyObj = json.get(key);
        if (keyObj == null || (keyObj != null && "".equals(keyObj.toString()))) {
            if (!flag) {
                errMap.put(keyName, " 不能为空");
            }
        } else {
            try {
                BigDecimal b = new BigDecimal(json.get(key).toString());
                rs = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            } catch (Exception e) {
                errMap.put(keyName, " 不是数字");
            }
        }
        return rs;
    }

}