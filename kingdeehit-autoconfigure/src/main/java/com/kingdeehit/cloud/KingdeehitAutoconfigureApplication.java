package com.kingdeehit.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.kingdeehit.cloud.autoconfigure.datasource.DataSourceTemplate;

@SpringBootApplication
public class KingdeehitAutoconfigureApplication {

	public static void main(String[] args) {
		SpringApplication.run(KingdeehitAutoconfigureApplication.class, args);
	}

}
