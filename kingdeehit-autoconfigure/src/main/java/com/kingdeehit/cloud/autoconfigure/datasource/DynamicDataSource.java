package com.kingdeehit.cloud.autoconfigure.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.web.client.RestTemplate;

import io.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

/**
 * Created by YHYR on 2017-12-25
 */

public class DynamicDataSource extends AbstractRoutingDataSource {
    private static DynamicDataSource instance;
    private static byte[] lock=new byte[0];
    private static Map<Object,Object> dataSourceMap=new HashMap<Object, Object>();

    @Override
    public void setTargetDataSources(Map<Object, Object> targetDataSources) {
        super.setTargetDataSources(targetDataSources);
        dataSourceMap.putAll(targetDataSources);
        super.afterPropertiesSet();// 必须添加该句，否则新添加数据源无法识别到
    }

    public Map<Object, Object> getDataSourceMap() {
        return dataSourceMap;
    }

    public static synchronized DynamicDataSource getInstance() {
        if(instance==null){
            synchronized (lock){
                if(instance==null){
                    instance=new DynamicDataSource();
                }
            }
        }
        return instance;
    }
    
    
    //必须实现其方法
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDBType();
    }
    
    
    @Autowired
    DataSourceTemplate dataSourceTemplate;
    
	@Autowired
    RestTemplate restTemplate;
	
	/**
	 * 重新加载数据源
	 * @return
	 * @throws Exception
	 */
	public Map<Object, Object> reloadDynamicDataSource() throws Exception {
		Map<Object, Object> dataSourceMap = getDataSourceMap();
		for (Object each : dataSourceMap.values()) {
        	ShardingDataSource shardingDataSource = (ShardingDataSource) each;
        	if(shardingDataSource != null) {
        		Map<String, DataSource> map = shardingDataSource.getDataSourceMap();
        		for (DataSource ds : map.values()) {
        			
        			ds.getClass().getDeclaredMethod("close").invoke(ds);
					/*
					 * DataSourceProxy psp = (DataSourceProxy) ds; DataSource dsp =
					 * psp.getTargetDataSource();
					 * dsp.getClass().getDeclaredMethod("close").invoke(dsp);
					 */
        		}
        		shardingDataSource.close();
        	}
        }
		DataSource datasource = dataSourceTemplate.getDataSourceMap("1", restTemplate);
		dataSourceMap.put("dynamic", datasource);
		setTargetDataSources(dataSourceMap);
		
		return dataSourceMap;
	}
	
	
	public int executeDDL(String sql) throws Exception {
		int execute = 0;
		Map<Object, Object> dataSourceMap = getDataSourceMap();
		for (Object each : dataSourceMap.values()) {
        	ShardingDataSource shardingDataSource = (ShardingDataSource) each;
        	if(shardingDataSource != null) {
        		Map<String, DataSource> map = shardingDataSource.getDataSourceMap();
        		for (DataSource ds : map.values()) {
        			Connection conn = ds.getConnection();
        			Statement ps =  conn.createStatement();
        			try {
        				execute = ps.executeUpdate(sql);
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
			            if(ps != null){
			            	ps.close();
			            }
			            if (conn != null) {
			            	conn.close();
			            }
			        }
        		}
        	}
        }
		return execute;
	}
}
