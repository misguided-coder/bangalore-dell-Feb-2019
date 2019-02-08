package com.example.trace;

import java.io.IOException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class ConsoleTraceMonitor {
	
	static long totalCount = 0;

	public static void main(String[] args) throws Exception {
		new ConsoleTraceMonitor();
	}

	public ConsoleTraceMonitor() throws Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(8000);
		factory.setUsername("guest");
		factory.setPassword("guest");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		Consumer consumer = new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {

				System.out.println(envelope.getRoutingKey());
				System.out.println(new String(body));
				System.out.println(properties);
				System.out.println("=====================================");
				if(envelope.getRoutingKey().startsWith("publish")) {
					totalCount++;
				}
				System.out.println("Total Count : "+totalCount);
			}

		};

		channel.basicConsume("AuditQ", true, consumer);

		while(true) {}
		
		//System.out.println("Done");

		//channel.close();
		//connection.close();

	}
}
