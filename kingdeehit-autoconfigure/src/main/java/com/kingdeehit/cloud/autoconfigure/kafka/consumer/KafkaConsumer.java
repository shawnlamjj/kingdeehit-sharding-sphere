package com.kingdeehit.cloud.autoconfigure.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kingdeehit.cloud.autoconfigure.datasource.DynamicDataSource;
import com.kingdeehit.cloud.autoconfigure.kafka.constant.KafkaConstants;

import lombok.extern.slf4j.Slf4j;


/**
 * @author wangjinliang on 2018/12/10.
 */
@Component
@Slf4j
public class KafkaConsumer {
	
    @KafkaListener(topics = {KafkaConstants.MY_TOPIC})
    public void receiveMessage(String message){
    	long start = System.currentTimeMillis();
    	log.info("KafkaConsumer开始， message：{}", message);
    	try {
    		//收到广播消息后通知执行方法
    		DynamicDataSource dynamicDataSource = DynamicDataSource.getInstance();
			dynamicDataSource.reloadDynamicDataSource();
    		
		} catch (Exception e) {
			log.info("KafkaConsumer发生异常，{}", e);
			e.printStackTrace();
		}
    	// 计算完成任务消耗时间
		double cost = (System.currentTimeMillis() - start) / 1000.000;
    	log.info("KafkaConsumer结束， 耗时：{}", cost + "秒");
    }
}
