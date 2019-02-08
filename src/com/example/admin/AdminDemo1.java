package com.example.admin;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AdminDemo1 {

	public static void main(String[] args) throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setVirtualHost("Automobile");
		factory.setPort(8000);
		factory.setUsername("ritesh");
		factory.setPassword("secret");

		Connection connection = factory.newConnection();
		System.out.println("Connected to Broker!!!!");

		Channel channel = connection.createChannel();

		channel.exchangeDeclare("ex.Bikes", "direct");
			
		Map<String, Object> features = new HashMap<String, Object>();
		features.put("x-message-ttl", 10000);
		features.put("x-expires", 60000);
		features.put("x-max-length", 20);
		features.put("x-overflow","drop-head");
		
		channel.queueDeclare("queue.CostlyBikes",true,false,true,features);

		channel.queueBind("queue.CostlyBikes","ex.Bikes","all-about-bikes");

		System.out.println("Finish Line!!!!!");

		channel.close();
		connection.close();

		System.out.println("Disconnected from Broker!!!!");

	}
}
