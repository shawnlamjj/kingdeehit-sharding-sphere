package com.kingdeehit.cloud.base.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "sharding_data_source_detail")
public class DataSourceDetailEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long dataSourceId;

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
    

    public Long getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(Long dataSourceId) {
		this.dataSourceId = dataSourceId;
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
