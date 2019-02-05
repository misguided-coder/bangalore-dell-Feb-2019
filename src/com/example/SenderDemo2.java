package com.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderDemo2 {

	public static void main(String[] args) throws Exception {
		new SenderDemo2();
	}

	public SenderDemo2() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(8000);
		factory.setUsername("guest");
		factory.setPassword("guest");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.basicPublish("StockEvents", "",null, "Dell - 6890".getBytes("UTF-8"));

		System.out.println("Done");

		channel.close();
		connection.close();

	}
}
