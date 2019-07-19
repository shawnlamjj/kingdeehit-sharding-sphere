package com.kingdeehit.cloud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.kingdeehit.cloud.autoconfigure.kafka.constant.KafkaConstants;
import com.kingdeehit.cloud.autoconfigure.kafka.producer.KafkaProducer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KingdeehitAutoconfigureApplicationTests {

//	@Autowired
//    private KafkaProducer kafkaProducer;

	@Test
	public void test() {
//	    for (int index = 0; index < 10; index ++) {
//            kafkaProducer.sendMessage(KafkaConstants.MY_TOPIC, "test" + index);
//        }
	}

}
