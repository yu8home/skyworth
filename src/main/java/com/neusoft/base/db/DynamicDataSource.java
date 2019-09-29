package com.neusoft.base.db;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * <pre>
 * 动态数据源
 * 1.注解：@DataSource(GlobalConst.DB_MASTER)
 * 2.编码：DynamicDataSource.setDsType(GlobalConst.DB_MASTER)
 * </pre>
 *
 * @author：yu8home
 * @date：2011年8月15日 下午11:43:50
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    private static final ThreadLocal<Object> contextHolder = new ThreadLocal<Object>();

    public static void setDsType(String key) {
        contextHolder.set(key);
    }

    public static String getDsType() {
        return (String) contextHolder.get();
    }

    public static void clearDbType() {
        contextHolder.remove();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSource.getDsType();
    }

    public DynamicDataSource(DataSource defaultTargetDataSource, Map<Object, Object> targetDataSources) {
        super.setDefaultTargetDataSource(defaultTargetDataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

}