package com.imooc.miaosha.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
	
	public static final String MIAOSHA_QUEUE = "miaosha.queue";
//	public static final String QUEUE = "queue";
//	public static final String TOPIC_QUEUE1 = "topic.queue1";
//	public static final String TOPIC_QUEUE2 = "topic.queue2";
//	public static final String HEADER_QUEUE = "header.queue";
//	public static final String TOPIC_EXCHANGE = "topicExchage";
//	public static final String FANOUT_EXCHANGE = "fanoutxchage";
//	public static final String HEADERS_EXCHANGE = "headersExchage";
	
	/**
	 * Direct模式 交换机Exchange
	 * 消息队列,
	 * Queue - 队列名字
	 * true - 是否要做持久化
	 * */
	@Bean
	public Queue queue() {
		return new Queue(MIAOSHA_QUEUE, true);
//		return new Queue(QUEUE, true);
	}
	
	/**
	 * Topic模式 交换机Exchange
	 * */
//	@Bean
//	public Queue topicQueue1() {
//		return new Queue(TOPIC_QUEUE1, true);
//	}
//	@Bean
//	public Queue topicQueue2() {
//		return new Queue(TOPIC_QUEUE2, true);
//	}
////	for TOPIC 交换机
//	@Bean
//	public TopicExchange topicExchage(){
//		return new TopicExchange(TOPIC_EXCHANGE);
//	}
//	//绑定queue到指定的topic exchange - 支持通配符
//	// with 后面接routine key
//	@Bean
//	public Binding topicBinding1() {
//		return BindingBuilder.bind(topicQueue1()).to(topicExchage()).with("topic.key1");
//	}
//	@Bean
//	public Binding topicBinding2() {
//		return BindingBuilder.bind(topicQueue2()).to(topicExchage()).with("topic.#");
//	}
//	/**
//	 * Fanout模式 交换机Exchange, 广播不用key了
//	 * */
//	@Bean
//	public FanoutExchange fanoutExchage(){
//		return new FanoutExchange(FANOUT_EXCHANGE);
//	}
//	@Bean
//	public Binding FanoutBinding1() {
//		return BindingBuilder.bind(topicQueue1()).to(fanoutExchage());
//	}
//	@Bean
//	public Binding FanoutBinding2() {
//		return BindingBuilder.bind(topicQueue2()).to(fanoutExchage());
//	}
//	/**
//	 * Header模式 交换机Exchange - 带条件的选队列, 条件是一个map key-value
//	 * */
//	@Bean
//	public HeadersExchange headersExchage(){
//		return new HeadersExchange(HEADERS_EXCHANGE);
//	}
//	@Bean
//	public Queue headerQueue1() {
//		return new Queue(HEADER_QUEUE, true);
//	}
//	@Bean
//	public Binding headerBinding() {
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("header1", "value1");
//		map.put("header2", "value2");
//		return BindingBuilder.bind(headerQueue1()).to(headersExchage()).whereAny(map).match();
//	}
//	
	
}
