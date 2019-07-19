package com.kingdeehit.cloud.autoconfigure.entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sharding_data_source_detail")
public class DataSourceDetailEntity {
    @Id
    private Long id;

    private String logicalTable;

    private String shardingColumn;

    private  String actualTable;

    private String actualDataNodes;

    private String databaseShardingColumn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogicalTable() {
        return logicalTable;
    }

    public void setLogicalTable(String logicalTable) {
        this.logicalTable = logicalTable;
    }

    public String getShardingColumn() {
        return shardingColumn;
    }

    public void setShardingColumn(String shardingColumn) {
        this.shardingColumn = shardingColumn;
    }

    public String getActualTable() {
        return actualTable;
    }

    public void setActualTable(String actualTable) {
        this.actualTable = actualTable;
    }

    public String getActualDataNodes() {
        return actualDataNodes;
    }

    public void setActualDataNodes(String actualDataNodes) {
        this.actualDataNodes = actualDataNodes;
    }

    public String getDatabaseShardingColumn() {
        return databaseShardingColumn;
    }

    public void setDatabaseShardingColumn(String databaseShardingColumn) {
        this.databaseShardingColumn = databaseShardingColumn;
    }
}
