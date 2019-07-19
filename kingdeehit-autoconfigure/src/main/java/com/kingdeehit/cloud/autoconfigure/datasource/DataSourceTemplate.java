package com.kingdeehit.cloud.autoconfigure.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.kingdeehit.cloud.autoconfigure.algorithm.ModuloDatabaseShardingAlgorithm;
import com.kingdeehit.cloud.autoconfigure.algorithm.ModuloTableShardingAlgorithm;
import com.kingdeehit.cloud.autoconfigure.entity.DataSourceDetailEntity;
import com.kingdeehit.cloud.autoconfigure.entity.DataSourceEntity;

import io.shardingsphere.api.config.MasterSlaveRuleConfiguration;
import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.TableRuleConfiguration;
import io.shardingsphere.api.config.strategy.ComplexShardingStrategyConfiguration;
import io.shardingsphere.core.rule.ShardingRule;
import io.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

@Component
public class DataSourceTemplate {
	
	@Value("${spring.datasource.initialSize}")
    private int initialSize;
	
	@Value("${spring.datasource.maxActive}")
	private int maxActive;
	
	@Value("${spring.datasource.minIdle}")
	private int minIdle;
	
	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;
	
	@Value("${spring.datasource.maxWait}")
	private int maxWait;
	
	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;
	
	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;
	
	@Value("${spring.datasource.removeAbandoned}")
	private boolean removeAbandoned;
	
	@Value("${spring.datasource.removeAbandonedTimeout}")
	private int removeAbandonedTimeout;
	
	@Value("${remote.dataSource.url}")
	private String remoteDataSourceUrl;
	
	
    /**
     * 获取指定数据库类型的数据库信息列表
     *
     * @param databaseType
     * @param restTemplate
     * @return
     * @throws SQLException
     */
    public DataSource getDataSourceMap(String databaseType, RestTemplate restTemplate) throws SQLException {
        ResponseEntity<List> responseEntity = restTemplate.getForEntity(remoteDataSourceUrl, List.class);
        List<DataSourceEntity> dataSources = JSON.parseArray(JSON.toJSONString(responseEntity.getBody()), DataSourceEntity.class);
        Map<String, Map<String, DataSource>> masterSlaveDataSource = new HashMap<>();
        Map<String, DataSourceDetailEntity> map = getLogicalDataSourceDetailMap(dataSources, masterSlaveDataSource);

        Map<String, javax.sql.DataSource> dataSourceMap = new HashMap<>();

        ShardingRuleConfiguration shardingRuleConfig = getShardingRuleConfiguration(map, masterSlaveDataSource, dataSourceMap);
        Properties shardingProperties = getProperties();
        return new ShardingDataSource(dataSourceMap, new ShardingRule(shardingRuleConfig, dataSourceMap.keySet()), new HashMap<>(), shardingProperties);
    }
    

    /**
     * 获取属性配置
     *
     * @return
     */
    private Properties getProperties() {
        Properties shardingProperties = new Properties();
        shardingProperties.put("sql.show", true);
        return shardingProperties;
    }

    /**
     * 获取组装好的ShardingRuleConfiguration
     *
     * @param detailMap
     * @return
     */
    private ShardingRuleConfiguration getShardingRuleConfiguration(Map<String, DataSourceDetailEntity> detailMap, Map<String,
            Map<String, javax.sql.DataSource>> masterSlaveDataSource, Map<String, javax.sql.DataSource> dataSourceMap) {
        // 分片规则配置文件类
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        
        //默认不分片规则库
        shardingRuleConfig.setDefaultDataSourceName("default");
        
        for (String logicTable : detailMap.keySet()) {
            DataSourceDetailEntity dataSourceDetail = detailMap.get(logicTable);
            // 表分片规则配置类
            TableRuleConfiguration ruleConfig = new TableRuleConfiguration();

            // 分片策略
            // 设置分库策略，缺省表示使用默认分库策略，即ShardingRuleConfiguration中配置的分库策略
            ComplexShardingStrategyConfiguration databaseShardingStrategyConfig = 
            		new ComplexShardingStrategyConfiguration(dataSourceDetail.getDatabaseShardingColumn(), new ModuloDatabaseShardingAlgorithm());
            
            ruleConfig.setDatabaseShardingStrategyConfig(databaseShardingStrategyConfig);
            // 分表规则
            ruleConfig.setTableShardingStrategyConfig(new ComplexShardingStrategyConfiguration(dataSourceDetail.getShardingColumn(), new ModuloTableShardingAlgorithm()));

            // 真实的数据节点
            String actualDataNodes = dataSourceDetail.getActualDataNodes();
            /*
              1) 由数据源名 + 表名组成 以小数点分割，多个表以逗号隔开，支持inline表达式
              2) 缺省时，以已知 数据源名称和逻辑表名称生成数据节点
              3) 用于广播表 --即每个库中都需要一个同样的表进行关联查询，多为字典表
              4) 只分库不分表且所有库的表结构完全一致的情况
             */
            //ruleConfig.setActualDataNodes(actualDataNodes);
            // 逻辑表名称
            ruleConfig.setLogicTable(logicTable);
            shardingRuleConfig.getTableRuleConfigs().add(ruleConfig);
        }

        for (String key : masterSlaveDataSource.keySet()) {
            Map<String, javax.sql.DataSource> temDatasourceMap = masterSlaveDataSource.get(key);
            //采取读写分离配置
            //MasterSlaveRuleConfiguration masterSlaveRuleConfiguration = new MasterSlaveRuleConfiguration();
            //masterSlaveRuleConfiguration.setName(key);
            for (String tKey : temDatasourceMap.keySet()) {
                if (tKey.contains("master")) {
                    //masterSlaveRuleConfiguration.setMasterDataSourceName(tKey);
                } else if (tKey.contains("slave")) {
               		//Collection<String> slaveDataSourceNames = new ArrayList<>();
                	//slaveDataSourceNames.add(tKey);
                    //masterSlaveRuleConfiguration.getSlaveDataSourceNames().add(tKey);
                	//masterSlaveRuleConfiguration.setSlaveDataSourceNames(slaveDataSourceNames);
                }
                dataSourceMap.put(tKey, temDatasourceMap.get(tKey));
            }
            //shardingRuleConfig.getMasterSlaveRuleConfigs().add(masterSlaveRuleConfiguration);
        }
        return shardingRuleConfig;
    }

    /**
     * Map<String, DataSourceDetail>  逻辑表名, 详细信息
     *
     * @param dataSources
     * @param masterSlaveDataSource
     * @return
     */
    private Map<String, DataSourceDetailEntity> getLogicalDataSourceDetailMap(List<DataSourceEntity> dataSources, Map<String, Map<String, DataSource>> masterSlaveDataSource) {
        Map<String, DataSourceDetailEntity> logicalMap = new HashMap<>();
        for (int i = 0; i < dataSources.size(); i++) {
            DataSourceEntity dataSource = dataSources.get(i);
            String dataSourceName = dataSource.getRuleName();

            //添加所有数据源
            if (masterSlaveDataSource.containsKey(dataSourceName)) {
                masterSlaveDataSource.get(dataSourceName).put(dataSource.getRuleName(), buildDataSource(dataSource));
            } else {
                Map<String, javax.sql.DataSource> stringDataSourceMap = new HashMap<>();
                masterSlaveDataSource.put(dataSourceName, stringDataSourceMap);
                stringDataSourceMap.put(dataSource.getRuleName(), buildDataSource(dataSource));
            }
            StringBuilder stringBuffer = new StringBuilder();
            
            if(dataSource.getDataSourceDetails() != null && dataSource.getDataSourceDetails().size() > 0) {
            	for (DataSourceDetailEntity dataSourceDetail : dataSource.getDataSourceDetails()) {
            		
            		String logicalTable = dataSourceDetail.getLogicalTable().toLowerCase();
            		String actualTable = dataSourceDetail.getActualTable().toLowerCase();
            		if (!logicalMap.containsKey(logicalTable)) {
            			stringBuffer.append(dataSourceName).append(".").append(actualTable);
            			dataSourceDetail.setActualDataNodes(stringBuffer.toString());
            			dataSourceDetail.setDatabaseShardingColumn(dataSource.getShardingColumn());
            			logicalMap.put(logicalTable, dataSourceDetail);
            		} else {
            			String actualDataNodes = logicalMap.get(logicalTable).getActualDataNodes();
            			stringBuffer.append(actualDataNodes).append(",").append(dataSourceName).append(".").append(actualTable);
            			logicalMap.get(logicalTable).setActualDataNodes(stringBuffer.toString());
            		}
            		stringBuffer.setLength(0);
            	}
            }

        }
        return logicalMap;
    }

    /**
     * 创建数据源
     *
     * @param dataSourceEntity
     * @return
     */
	private javax.sql.DataSource buildDataSource(DataSourceEntity dataSourceEntity) {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceEntity.getDriverClass());
        dataSource.setUrl(dataSourceEntity.getUrl());
        dataSource.setUsername(dataSourceEntity.getUserName());
        dataSource.setPassword(dataSourceEntity.getPassword());
        dataSource.setMaxActive(maxActive);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        dataSource.setMaxWait(maxWait);
        dataSource.setValidationQuery("select 'x'");
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setRemoveAbandoned(removeAbandoned);
        dataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        
        return dataSource;
    }
	
}
