package com.kingdeehit.cloud.autoconfigure.config;

import com.kingdeehit.cloud.autoconfigure.datasource.DataSourceContextHolder;
import com.kingdeehit.cloud.autoconfigure.datasource.DataSourceTemplate;
import com.kingdeehit.cloud.autoconfigure.datasource.DatabaseType;
import com.kingdeehit.cloud.autoconfigure.datasource.DynamicDataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by YHYR on 2017-12-25
 */

@Configuration
public class DataSourceConfig {
    
    
    @Bean
	@ConditionalOnMissingBean
    public DataSourceTemplate dataSourceTemplate() {
    	return new DataSourceTemplate();
    }
	
	@Bean
	@ConditionalOnMissingBean
    public RestTemplate restTemplate() {
		return new RestTemplate();
    }

    @Bean
    public DynamicDataSource dynamicDataSource() throws Exception {
    	DataSourceContextHolder.setDBType(DatabaseType.BASE);
        DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
        DataSource datasource =  dataSourceTemplate().getDataSourceMap("1", restTemplate());

        Map<Object,Object> map = new HashMap<>();
        map.put("dynamic", datasource);
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(datasource);
        

        return dynamicDataSource;
    }

}
