package com.rabbitMq.producer.production;

public class Message {

	private String sender;

	private String text;

	public Message() {
	}

	public Message(String name, String text) {
		this.sender = name;
		this.text = text;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String name) {
		this.sender = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Message [sender=" + sender + ", text=" + text + "]";
	}

}
