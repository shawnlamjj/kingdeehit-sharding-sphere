package com.kingdeehit.cloud.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kingdeehit.cloud.base.properties.BaseDataSourceProperties;
import com.kingdeehit.cloud.base.util.DataSourceBuildUtil;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DataSourceConfig {
    @Bean
    public DataSource dataSource(BaseDataSourceProperties properties) throws SQLException {
        return DataSourceBuildUtil.build(properties);
    }
}
