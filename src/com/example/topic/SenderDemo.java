package com.example.topic;

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

		//channel.basicPublish("AllAboutCars", "cars.suv.german",null, "BMW X6".getBytes("UTF-8"));
		//channel.basicPublish("AllAboutCars", "cars.suv.german",null, "BMW X1".getBytes("UTF-8"));
		//channel.basicPublish("AllAboutCars", "cars.suv.german",null, "Audi A5".getBytes("UTF-8"));

		//channel.basicPublish("AllAboutCars", "cars.india.cheap",null, "Maruti Desire".getBytes("UTF-8"));
		
		channel.basicPublish("AllAboutCars", "cars.india.cheap",null, "Maruti Desire".getBytes("UTF-8"));

		System.out.println("Done");

		channel.close();
		connection.close();

	}
}
