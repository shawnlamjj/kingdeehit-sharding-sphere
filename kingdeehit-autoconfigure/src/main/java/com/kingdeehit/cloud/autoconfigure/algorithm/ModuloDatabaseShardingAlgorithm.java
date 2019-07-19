package com.kingdeehit.cloud.autoconfigure.algorithm;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.shardingsphere.api.algorithm.sharding.ShardingValue;

public class ModuloDatabaseShardingAlgorithm extends AbstractShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection<String> collection, Collection<ShardingValue> shardingValues) {
    	//获取分片值
        Collection<String> values = getShardingValue(shardingValues);
        List<String> shardingSuffix = new ArrayList<>();
        for (String databaseName : collection) {
            for (String databaseName1 : values) {
                if (databaseName.equals(databaseName1)) {
                    shardingSuffix.add(databaseName1);
                }
            }
        }
        return shardingSuffix;
    }
}
