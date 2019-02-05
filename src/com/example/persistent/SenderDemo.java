package com.example.persistent;

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
		
		
		
		BasicProperties properties = new BasicProperties();
		properties = properties.builder().deliveryMode(2).build();
		
	
		//Step 4 ------ send the message to exchange
		//channel.basicPublish("", "HindiNews",properties, "2 murders in Muzaffarnagar".getBytes("UTF-8"));
		//channel.basicPublish("", "HindiNews",null, "5 Lac no tax".getBytes("UTF-8"));

		//channel.basicPublish("", "AuctionQ",properties, "Sony LED-Rs/- 10 only till 5 PM".getBytes("UTF-8"));
		//channel.basicPublish("", "AuctionQ",properties, "LG Refrigerator- Rs/- 100 only till 5 PM".getBytes("UTF-8"));

		channel.basicPublish("", "cars",properties, "5 Land Rover".getBytes("UTF-8"));
		channel.basicPublish("", "cars",properties, "2 Jaguar".getBytes("UTF-8"));

		
		System.out.println("Done");

		channel.close();
		connection.close();
		System.out.println("Disconnected from Broker!!!!");

	}
}
