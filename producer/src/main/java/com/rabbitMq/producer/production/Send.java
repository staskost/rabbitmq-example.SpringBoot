package com.rabbitMq.producer.production;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {

	private final static String QUEUE_NAME = "MyQueue";

	public static void send(Message msg) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		try (Connection connection = factory.newConnection(); Channel channel = connection.createChannel()) {
			channel.queueDeclare(QUEUE_NAME, true, false, false, null);
			channel.basicPublish("MyTopicExchange", "topic", null, msg.toString().getBytes("UTF-8"));
			System.out.println("Message sent ");
		}
	}

}
