package com.kingdeehit.cloud.autoconfigure.kafka.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wangjinliang on 2018/12/10.
 */
@Component
public class KafkaProducer {
	
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String topic, String message){
        kafkaTemplate.send(topic, message);
    }
}
