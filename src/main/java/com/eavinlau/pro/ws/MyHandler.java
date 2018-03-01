package com.eavinlau.pro.ws;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.StringDecoder;
import kafka.utils.VerifiableProperties;

public class MyHandler extends TextWebSocketHandler {

	public static Map<WebSocketSession, ConsumerConnector> SC = new HashMap<>();
	
	public static Properties props = new Properties();
	public static ConsumerConfig config = null;
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) {
    	try {
    		//zookeeper 配置
            props.put("zookeeper.connect", "10.0.8.82:2181");
            //group 代表一个消费组
            props.put("group.id", "g"+new Date().getTime());
            //zk连接超时
            props.put("zookeeper.session.timeout.ms", "10000");
            props.put("zookeeper.sync.time.ms", "200");
            props.put("auto.commit.interval.ms", "1000");
            props.put("serializer.class", "kafka.serializer.StringEncoder");

            config = new ConsumerConfig(props);
            ConsumerConnector consumer = Consumer.createJavaConsumerConnector(config);
    		
    		SC.put(session, consumer);
    		
            Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
            
            topicCountMap.put("olsdk_pagepath", new Integer(1));
            
            StringDecoder keyDecoder = new StringDecoder(new VerifiableProperties());
            StringDecoder valueDecoder = new StringDecoder(new VerifiableProperties());
            
            Map<String, List<KafkaStream<String, String>>> consumerMap = consumer.createMessageStreams(topicCountMap,keyDecoder,valueDecoder);
            
            List<KafkaStream<String, String>> streams = consumerMap.get("olsdk_pagepath");
            
            for (final KafkaStream<String, String> stream : streams) {
    			ConsumerIterator<String, String> it = stream.iterator();
    			int count=0;
    			while (it.hasNext()){
    				if(count>=10) {
    					break;
    				}
    				MessageAndMetadata<String, String> thisMetadata=it.next();
    				String s = thisMetadata.message();
    				if(s.contains(message.getPayload())) {
    					session.sendMessage(new TextMessage(s));
    					count++;
    				} 
    			}
    		}
            consumer.shutdown();
            session.sendMessage(new TextMessage("会话："+session.getId()+"结束"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    //连接建立后处理
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	
    }
    
    //抛出异常时处理
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
    	ConsumerConnector c1 = SC.get(session);
    	if(c1!=null) c1.shutdown();
        if(session.isOpen()){
            session.close();
        }
    }
    
    //连接关闭后处理
    public void afterConnectionClosed(WebSocketSession session) throws Exception {
    	ConsumerConnector c1 = SC.get(session);
    	if(c1!=null) c1.shutdown();
    	if(session.isOpen()){
            session.close();
        }
    }

}