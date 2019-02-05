package com.example;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class QueueCreationDemo {

	public static void main(String[] args) throws Exception {
		new QueueCreationDemo();
	}

	public QueueCreationDemo() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(8000);
		factory.setUsername("guest");
		factory.setPassword("guest");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		for (int i = 1; i <= 50; i++) {
			
			channel.queueDeclare("queue.wallmart.retail.india."+i, true, false, true, null);
			System.out.printf("Queue Created with name : %s%n","queue.wallmart.retail.india."+i);
			TimeUnit.MILLISECONDS.sleep(500);	
		}
	
		System.out.println("Done");

		channel.close();
		connection.close();

	}
}
