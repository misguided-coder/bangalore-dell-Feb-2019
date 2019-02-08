package com.example.trace;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class FileTraceMonitor {
	
	static long totalCount = 0;

	public static void main(String[] args) throws Exception {
		new FileTraceMonitor();
	}

	public FileTraceMonitor() throws Exception {
		
		FileWriter fileWriter = new FileWriter("C:\\Users\\hi2ty\\AppData\\Roaming\\RabbitMQ\\log\\trace.log",true);

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

				
				if(new String(body).contains("Java")) {

					fileWriter.write("Activity : "+envelope.getRoutingKey());
					fileWriter.write("\n");
					fileWriter.write("Activity Time : "+LocalDateTime.now());
					fileWriter.write("\n");
					fileWriter.write("Body : "+new String(body));
					fileWriter.write("\n");
					fileWriter.write("Headers : "+properties);
					fileWriter.write("\n");
					fileWriter.write("=====================================");

				}
				
				fileWriter.write("\n");
				
				if(envelope.getRoutingKey().startsWith("publish")) {
					totalCount++;
				}
				fileWriter.write("Total Count : "+totalCount);
				fileWriter.write("\n");
				fileWriter.flush();
			}

		};

		channel.basicConsume("AuditQ", true, consumer);

		while(true) {}
		
		//System.out.println("Done");
		//fileWriter.close();
		//channel.close();
		//connection.close();

	}
}
