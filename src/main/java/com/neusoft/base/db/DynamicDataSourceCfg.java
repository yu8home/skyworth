package com.neusoft.base.db;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.neusoft.base.comm.GlobalConst;

// @Configuration
public class DynamicDataSourceCfg {

    // @Bean
    // @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDS() {
        return DruidDataSourceBuilder.create().build();
    }

    // @Bean
    // @ConfigurationProperties("spring.datasource.druid.slave")
    public DataSource slaveDS() {
        return DruidDataSourceBuilder.create().build();
    }

    // @Bean
    // @Primary
    public DynamicDataSource dataSource(DataSource masterDS, DataSource slaveDS) {
        Map<Object, Object> t = new HashMap<>();
        t.put(GlobalConst.DB_MASTER, masterDS);
        t.put(GlobalConst.DB_SLAVE, slaveDS);
        return new DynamicDataSource(masterDS, t);
    }

}