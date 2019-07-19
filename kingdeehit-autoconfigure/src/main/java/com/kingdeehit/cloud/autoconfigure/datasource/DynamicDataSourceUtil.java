package com.kingdeehit.cloud.autoconfigure.datasource;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by YHYR on 2017-12-25
 */

@Slf4j
public class DynamicDataSourceUtil {
	
	/**
	 * 执行更新数据结构ddl操作
	 * @param sql
	 * @return
	 */
	public static int executeDDL(String sql) {
		DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
		int result = 0;
		try {
			result = dynamicDataSource.executeDDL(sql);
			log.info("executeDDL执行成功！");
		} catch (Exception e) {
			log.info("executeDDL执行异常，{}", e);
		}
		return result;
	}
	
	/**
	 * 重新加载动态数据源
	 * @return
	 * @throws Exception
	 */
	public static Map<Object, Object> reloadDynamicDataSource() {
		DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
		Map<Object, Object> dataSourceMap = new HashMap<Object, Object>();
		try {
			dataSourceMap = dynamicDataSource.reloadDynamicDataSource();
			log.info("reloadDynamicDataSource执行成功！");
		} catch (Exception e) {
			log.info("reloadDynamicDataSource执行异常，{}", e);
		}
		return dataSourceMap;
	}
	
}
