package com.kingdeehit.cloud.autoconfigure.algorithm;

import io.shardingsphere.api.algorithm.sharding.ShardingValue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ModuloTableShardingAlgorithm extends AbstractShardingAlgorithm {
    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, Collection<ShardingValue> shardingValues) {
        List<String> shardingSuffix = new ArrayList<>();
        
        return availableTargetNames;
    }

}
