package com.kingdeehit.cloud.autoconfigure.entity;

import javax.persistence.*;

import java.util.List;

@Table(name = "sharding_data_source")
public class DataSourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name")
    private String ruleName;
    @Column(name = "url")
    private String url;
    @Column(name = "databaseType")
    private String databaseType;
    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "driverClass")
    private String driverClass;
    @Column(name = "masterSlaveType")
    private String masterSlaveType;
    @Column(name = "shardingColumn")
    private String shardingColumn;

    private List<DataSourceDetailEntity> dataSourceDetails;

    public String getShardingColumn() {
        return shardingColumn;
    }

    public void setShardingColumn(String shardingColumn) {
        this.shardingColumn = shardingColumn;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getDatabaseType() {
        return databaseType;
    }

    public void setDatabaseType(String databaseType) {
        this.databaseType = databaseType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getMasterSlaveType() {
        return masterSlaveType;
    }

    public void setMasterSlaveType(String masterSlaveType) {
        this.masterSlaveType = masterSlaveType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<DataSourceDetailEntity> getDataSourceDetails() {
        return dataSourceDetails;
    }

    public void setDataSourceDetails(List<DataSourceDetailEntity> dataSourceDetails) {
        this.dataSourceDetails = dataSourceDetails;
    }
}
