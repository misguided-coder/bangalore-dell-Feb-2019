package com.example.queue;

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

		for (int i = 1; i <= 50; i++) {
			//int otp  = 1000+i;
			//Integer	it = new Integer(otp);
			//channel.basicPublish("", "AuctionQ", null, "10 BMW in Rs/- 100".getBytes("UTF-8"));
			
			channel.basicPublish("", "queue.cars.hyundai", null, "10 BMW in Rs/- 100".getBytes("UTF-8"));
			
			//channel.basicPublish("", "PizzaQ", null, "1 Country Special Cheese Burst".getBytes("UTF-8"));
			//channel.basicPublish("", "OTP-Q", null, it.toString().getBytes() );
			//channel.basicPublish("", "RestaurantQ", null, (i+"-Paneer Tikka").toString().getBytes() );
			//channel.basicPublish("", "MoviesQ", null, "Robot".getBytes() );
			System.out.println("Message Sent : "+i);
			TimeUnit.MILLISECONDS.sleep(500);	
		}
	
		//channel.basicPublish("", "CricketQ", null, "India-England match at 6 PM".getBytes() );
		//channel.basicPublish("", "CricketQ", null, "India-Bangladesh match at 4 PM".getBytes() );
		
		System.out.println("Done");

		channel.close();
		connection.close();

	}
}
