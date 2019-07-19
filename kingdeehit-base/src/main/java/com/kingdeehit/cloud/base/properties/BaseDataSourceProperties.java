package com.kingdeehit.cloud.base.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "db.mysql")
public class BaseDataSourceProperties extends DataSourceProperties {
}
