package com.kingdeehit.cloud.base.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kingdeehit.cloud.base.entity.DataSourceDetailEntity;
import com.kingdeehit.cloud.base.entity.DataSourceEntity;
import com.kingdeehit.cloud.base.mapper.DataSourceMapper;
import com.kingdeehit.cloud.base.service.BaseDataSourceService;

import java.util.List;

@Service("bseDataSourceService")
public class BaseDataSourceServiceImpl implements BaseDataSourceService {
    @Autowired
    private DataSourceMapper dataSourceMapper;

    @Override
    public List<DataSourceEntity> getDataSources(String databaseType) {
        return dataSourceMapper.getDataSources(databaseType);
    }

	@Override
	public void addDataSource(DataSourceEntity dataSourceEntity) {
		dataSourceMapper.addDataSource(dataSourceEntity);
	}

	@Override
	public void addDataSourceDetail(DataSourceDetailEntity dataSourceDetailEntity) {
		dataSourceMapper.addDataSourceDetail(dataSourceDetailEntity);
	}

}
