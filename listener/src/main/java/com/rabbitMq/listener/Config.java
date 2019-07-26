package com.rabbitMq.listener;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
	private static final String MY_QUEUE = "MyQueue";

	@Bean
	Queue myQueue() {
		return new Queue(MY_QUEUE, true);
	}

	@Bean
	Exchange myExchange() {
		return ExchangeBuilder.topicExchange("MyTopicExchange").durable(true).build();
	}

	@Bean
	Binding queueBinding() {
		return BindingBuilder.bind(myQueue()).to(myExchange()).with("topic") 
				.noargs();
	}

	@Bean
	ConnectionFactory cf() {
		CachingConnectionFactory ccf = new CachingConnectionFactory("localhost");
		ccf.setUsername("guest");
		ccf.setPassword("guest");
		return ccf;
	}

	@Bean
	MessageListenerContainer mlc() {
		SimpleMessageListenerContainer smlc = new SimpleMessageListenerContainer();
		smlc.setConnectionFactory(cf());
		smlc.setQueues(myQueue());
		smlc.setMessageListener(new RabbitMQListener());
		return smlc;
	}
}
