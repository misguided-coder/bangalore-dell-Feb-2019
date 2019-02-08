package com.example.trace;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderDemo {

	public static void main(String[] args) throws Exception {
		new SenderDemo();
	}

	public SenderDemo() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(8000);
		factory.setUsername("guest");
		factory.setPassword("guest");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		for (int i = 1; i <= 100; i++) {
			channel.basicPublish("Books", "java", null, "Java in Action".getBytes("UTF-8"));
			System.out.println("Message Sent : " + i);
			TimeUnit.MILLISECONDS.sleep(200);
		}

		System.out.println("Done");

		channel.close();
		connection.close();

	}
}
