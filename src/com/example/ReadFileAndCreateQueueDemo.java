package com.example;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.TimeUnit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class ReadFileAndCreateQueueDemo {

	public static void main(String[] args) throws Exception {
		new ReadFileAndCreateQueueDemo();
	}

	public ReadFileAndCreateQueueDemo() throws Exception {

		Path path = FileSystems.getDefault().getPath("C:/banglore-dell-workspace/training/files/", "queues.txt");

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setPort(8000);
		factory.setUsername("guest");
		factory.setPassword("guest");

		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		Files.lines(path).forEach((line) -> {

			try {
				channel.queueDeclare(line, true, false, true, null);
				System.out.printf("Queue Created with name : %s%n", line);
				TimeUnit.MILLISECONDS.sleep(500);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		});

		System.out.println("Done");

		channel.close();
		connection.close();

	}
}
