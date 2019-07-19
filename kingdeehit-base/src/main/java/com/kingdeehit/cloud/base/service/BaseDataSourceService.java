package com.kingdeehit.cloud.base.service;


import java.util.List;

import com.kingdeehit.cloud.base.entity.DataSourceDetailEntity;
import com.kingdeehit.cloud.base.entity.DataSourceEntity;



public interface BaseDataSourceService {
    List<DataSourceEntity> getDataSources(String databaseType);
    
    void addDataSource(DataSourceEntity dataSourceEntity);
    
    void addDataSourceDetail(DataSourceDetailEntity dataSourceDetailEntity);

}
