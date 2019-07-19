package com.kingdeehit.cloud.autoconfigure.algorithm;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import io.shardingsphere.api.algorithm.sharding.ListShardingValue;
import io.shardingsphere.api.algorithm.sharding.ShardingValue;
import io.shardingsphere.api.algorithm.sharding.complex.ComplexKeysShardingAlgorithm;

abstract class AbstractShardingAlgorithm implements ComplexKeysShardingAlgorithm {
    Collection<String> getShardingValue(Collection<ShardingValue> shardingValues) {
        Collection<String> valueSet = new ArrayList<>();
        for (ShardingValue next : shardingValues) {
            if (next instanceof ListShardingValue) {
                ListShardingValue value = (ListShardingValue) next;
                return value.getValues();
            }
        }
        return valueSet;
    }
}
