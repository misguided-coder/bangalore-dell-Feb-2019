package com.example.admin;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class SenderDemo1 {

	public static void main(String[] args) {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setVirtualHost("Automobile");
		factory.setPort(8000);
		factory.setUsername("uday");
		factory.setPassword("secret");

		Connection connection = null;
		Channel channel = null;

		try {
			connection = factory.newConnection();

			channel = connection.createChannel();
			
			channel.basicQos(20);

			channel.basicPublish("ex.Bikes", "all-bikes", null, "2 BMW Bikes".getBytes("UTF-8"));

			//int i = 10 / 0;

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} finally {
			try {

				if (channel != null && channel.isOpen()) {
					channel.close();
					System.out.println("Channel Done!!!");
				}
				if (connection != null && connection.isOpen()) {
					connection.close();
					System.out.println("Connection Done!!!");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (TimeoutException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Finish Line!!!");


	}
}
