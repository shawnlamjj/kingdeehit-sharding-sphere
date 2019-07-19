package com.kingdeehit.cloud.base.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.kingdeehit.cloud.base.properties.DataSourceProperties;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceBuildUtil {
	
    public static DataSource build(DataSourceProperties dataSourcesProperties) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(dataSourcesProperties.getUrl());
        dataSource.setUsername(dataSourcesProperties.getUsername());
        dataSource.setPassword(dataSourcesProperties.getPassword());
        dataSource.setDriverClassName(dataSourcesProperties.getDriverClass());
        if (dataSourcesProperties.getInitialSize() > 0) {
            dataSource.setInitialSize(dataSourcesProperties.getInitialSize());
        }

        if (dataSourcesProperties.getMinIdle() > 0) {
            dataSource.setMinIdle(dataSourcesProperties.getMinIdle());
        }

        if (dataSourcesProperties.getMaxActive() > 0) {
            dataSource.setMaxActive(dataSourcesProperties.getMaxActive());
        }

        //配置druid相关的保持心跳参数
        if (dataSourcesProperties.getTimeBetweenEvictionRunsMillis() > 0) {
            dataSource.setTimeBetweenEvictionRunsMillis(dataSourcesProperties.getTimeBetweenEvictionRunsMillis());
        }

        if (dataSourcesProperties.getMinEvictableIdleTimeMillis() > 0) {
            dataSource.setMinEvictableIdleTimeMillis(dataSourcesProperties.getMinEvictableIdleTimeMillis());
        }
        dataSource.setValidationQuery(dataSourcesProperties.getValidationQuery());
        dataSource.setTestOnBorrow(dataSourcesProperties.isTestOnBorrow());
        dataSource.setTestOnReturn(dataSourcesProperties.isTestOnReturn());
        dataSource.setTestWhileIdle(dataSourcesProperties.isTestWhileIdle());
        //end
        try {
            dataSource.init();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return dataSource;
    }
}
