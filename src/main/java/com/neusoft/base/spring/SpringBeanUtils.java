package com.neusoft.base.spring;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * ApplicationContext工具类
 *
 * @author：yu8home
 * @date：2017年9月20日 下午1:36:21
 */
@Component
@SuppressWarnings({ "rawtypes" })
public class SpringBeanUtils implements ApplicationContextAware {
    public static ApplicationContext ac;

    private SpringBeanUtils() {
        super();
    }

    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        if (SpringBeanUtils.ac == null) {
            SpringBeanUtils.ac = ac;
        }
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return ac.getBean(name, clazz);
    }

    public static <T> T getBean(Class<T> clazz) {
        T t = null;
        Map<String, T> map = ac.getBeansOfType(clazz);
        for (Map.Entry<String, T> entry : map.entrySet()) {
            t = entry.getValue();
        }
        return t;
    }

    public static boolean containsBean(String beanName) {
        return ac.containsBean(beanName);
    }

    public static boolean isSingleton(String beanName) {
        return ac.isSingleton(beanName);
    }

    public static Class getType(String beanName) {
        return ac.getType(beanName);
    }

}