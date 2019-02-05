package com.example.header;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderDemo {

	public static void main(String[] args) throws Exception {
		new SenderDemo();
	}

	public SenderDemo() throws Exception {

		//Step 1 ----- Set up a factory for getting connection to broker
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(8000);
		factory.setUsername("guest");
		factory.setPassword("guest");

		//Step 2 ------ get connection to the brocker
		Connection connection = factory.newConnection();
		System.out.println("Connected to Broker!!!!");
		
		//Step 3 ------ open a session for doing messaging
		Channel channel = connection.createChannel();
		
		
		//Option Step ------ pepare headers to be sent inside message
		Map<String, Object> customHeaders = new HashMap<>();
		customHeaders.put("lang", "hi");
		//customHeaders.put("type", "crime");
		
		BasicProperties properties = new BasicProperties();
		properties = properties.builder().headers(customHeaders).build();
		
	
		//Step 4 ------ send the message to exchange
		//channel.basicPublish("News", "",properties, "2 murders in Muzaffarnagar".getBytes("UTF-8"));
		channel.basicPublish("News", "",properties, "5 Lac no tax".getBytes("UTF-8"));

		System.out.println("Done");

		channel.close();
		connection.close();
		System.out.println("Disconnected from Broker!!!!");

	}
}
