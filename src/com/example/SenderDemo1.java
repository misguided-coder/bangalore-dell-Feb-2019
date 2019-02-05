package com.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderDemo1 {

	public static void main(String[] args) throws Exception {
		new SenderDemo1();
	}

	public SenderDemo1() throws Exception {

		String ROUTING_KEY1 = "java";
		String ROUTING_KEY2 = "spring";
		

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(8000);
		factory.setUsername("guest");
		factory.setPassword("guest");
		
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		
	
		channel.basicPublish("Books", ROUTING_KEY1, null, "Java in Action".getBytes("UTF-8"));
		
		//channel.basicPublish("Books", ROUTING_KEY1, null, "Java in 21 Days".getBytes("UTF-8"));

		//channel.basicPublish("Books", ROUTING_KEY2, null, "Spring in Action".getBytes("UTF-8"));
		//channel.basicPublish("Books", ROUTING_KEY2, null, "Spring in 5 Days".getBytes("UTF-8"));

		System.out.println("Done");

		channel.close();
		connection.close();

	}
}
