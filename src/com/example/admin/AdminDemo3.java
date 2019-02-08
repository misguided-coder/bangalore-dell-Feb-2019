package com.example.admin;

import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AdminDemo3 {

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

		channel.queueBind("queue.SportyBikes", "ex.Bikes", "all-bikes");
		
		System.out.println("Finish Line!!!!!");

		channel.close();
		connection.close();

		System.out.println("Disconnected from Broker!!!!");

	}
}
