package com.rabbitMq.producer.production;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MessageProduction implements ApplicationListener<ContextRefreshedEvent>{

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		Message msg = new Message("Staskost", "Hello World");
		try {
			Send.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
