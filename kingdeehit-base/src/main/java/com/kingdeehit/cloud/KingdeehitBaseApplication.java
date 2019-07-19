package com.kingdeehit.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.kingdeehit.cloud.base.config.DataSourceConfig;
import com.spring4all.swagger.EnableSwagger2Doc;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableSwagger2Doc
@ImportAutoConfiguration(DataSourceConfig.class)
@MapperScan("com.kingdeehit.cloud.base.mapper")
public class KingdeehitBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(KingdeehitBaseApplication.class, args);
	}

}
