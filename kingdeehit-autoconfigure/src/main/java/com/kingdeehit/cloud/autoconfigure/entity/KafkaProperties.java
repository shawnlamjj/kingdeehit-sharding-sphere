package com.kingdeehit.cloud.autoconfigure.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "kafka")
public class KafkaProperties {
	
	private String consumerServers;
	private String consumerEnableAutoCommit;
	private String consumerSessionTimeout;
	private String consumerAutoCommitInterval;
	private String consumerAutoOffsetReset;
	private String consumerGroupId;
	
	private String producerServers;
	private String producerRetries;
	private String producerBatchSize;
	private String producerLinger;
	private String producerBufferMemory;
	
	
	public String getConsumerServers() {
		return consumerServers;
	}
	public void setConsumerServers(String consumerServers) {
		this.consumerServers = consumerServers;
	}
	public String getConsumerEnableAutoCommit() {
		return consumerEnableAutoCommit;
	}
	public void setConsumerEnableAutoCommit(String consumerEnableAutoCommit) {
		this.consumerEnableAutoCommit = consumerEnableAutoCommit;
	}
	public String getConsumerSessionTimeout() {
		return consumerSessionTimeout;
	}
	public void setConsumerSessionTimeout(String consumerSessionTimeout) {
		this.consumerSessionTimeout = consumerSessionTimeout;
	}
	public String getConsumerAutoCommitInterval() {
		return consumerAutoCommitInterval;
	}
	public void setConsumerAutoCommitInterval(String consumerAutoCommitInterval) {
		this.consumerAutoCommitInterval = consumerAutoCommitInterval;
	}
	public String getConsumerAutoOffsetReset() {
		return consumerAutoOffsetReset;
	}
	public void setConsumerAutoOffsetReset(String consumerAutoOffsetReset) {
		this.consumerAutoOffsetReset = consumerAutoOffsetReset;
	}
	public String getConsumerGroupId() {
		return consumerGroupId;
	}
	public void setConsumerGroupId(String consumerGroupId) {
		this.consumerGroupId = consumerGroupId;
	}
	public String getProducerServers() {
		return producerServers;
	}
	public void setProducerServers(String producerServers) {
		this.producerServers = producerServers;
	}
	public String getProducerRetries() {
		return producerRetries;
	}
	public void setProducerRetries(String producerRetries) {
		this.producerRetries = producerRetries;
	}
	public String getProducerBatchSize() {
		return producerBatchSize;
	}
	public void setProducerBatchSize(String producerBatchSize) {
		this.producerBatchSize = producerBatchSize;
	}
	public String getProducerLinger() {
		return producerLinger;
	}
	public void setProducerLinger(String producerLinger) {
		this.producerLinger = producerLinger;
	}
	public String getProducerBufferMemory() {
		return producerBufferMemory;
	}
	public void setProducerBufferMemory(String producerBufferMemory) {
		this.producerBufferMemory = producerBufferMemory;
	}
	
	

}
