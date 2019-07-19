package com.kingdeehit.cloud.base.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kingdeehit.cloud.base.entity.DataSourceDetailEntity;
import com.kingdeehit.cloud.base.entity.DataSourceEntity;
import com.kingdeehit.cloud.base.service.BaseDataSourceService;
import com.kingdeehit.cloud.vo.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

@Api(tags = {"数据源服务"})
@RestController
@RequestMapping("/base/datasource")
public class BaseDataSourceController {
    @Autowired
    private BaseDataSourceService dataSourceService;

    @ApiOperation(value = "查询数据源数据")
    @GetMapping("/{databaseType}/getDataSources")
    public List<DataSourceEntity> getDataSources(@PathVariable String databaseType) {
        return dataSourceService.getDataSources(databaseType);
    }
    
    @ApiOperation(value = "添加数据源数据，每次上传不允许超过100条记录")
    @PostMapping(path = "/save")
    public Response<?> add(@RequestBody List<DataSourceEntity> events) {
    	if(events != null && events.size() > 0) {
    		for (DataSourceEntity dataSourceEntity : events) {
    			dataSourceService.addDataSource(dataSourceEntity);
    		}
    		return Response.createSuccess(null);
    	}
    	return Response.createError(null);
    }
    
    @ApiOperation(value = "添加数据源分片详情，每次上传不允许超过100条记录")
    @PostMapping(path = "/saveDetail")
    public Response<?> saveDetail(@RequestBody List<DataSourceDetailEntity> events) {
    	if(events != null && events.size() > 0) {
	    	for (DataSourceDetailEntity dataSourceEntity : events) {
	    		dataSourceService.addDataSourceDetail(dataSourceEntity);
			}
	    	return Response.createSuccess(null);
    	}
    	return Response.createError(null);
    }
    
}
