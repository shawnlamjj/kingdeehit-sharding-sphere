package com.kingdeehit.cloud.autoconfigure.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kingdeehit.cloud.autoconfigure.datasource.DynamicDataSourceUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = {"数据源服务"})
@RestController
@RequestMapping("/autoconfigure")
public class AutoconfigureController {

	 	@ApiOperation(value = "执行数据库DDL语句")
	    @PostMapping(path = "/datasource/execute")
	 	@ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "statement", value = "DDL语句", required = true)})
	    public String execute(@RequestParam(required = true) String statement) {
	 		int result = DynamicDataSourceUtil.executeDDL(statement);
	 		return "ok";
	    }
}
