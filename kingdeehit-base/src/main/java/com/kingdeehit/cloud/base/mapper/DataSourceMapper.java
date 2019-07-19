package com.kingdeehit.cloud.base.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

import com.kingdeehit.cloud.base.entity.DataSourceDetailEntity;
import com.kingdeehit.cloud.base.entity.DataSourceEntity;


public interface DataSourceMapper extends Mapper<DataSourceEntity>, MySqlMapper<DataSourceEntity> {

    List<DataSourceEntity> getDataSources(String databaseType);
    
    void addDataSource(DataSourceEntity dataSourceEntity);
    
    void addDataSourceDetail(DataSourceDetailEntity dataSourceDetailEntity);

}
