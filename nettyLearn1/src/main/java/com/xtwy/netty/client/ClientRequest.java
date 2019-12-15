package com.xtwy.netty.client;

import java.util.concurrent.atomic.AtomicLong;

public class ClientRequest {
	private final Long id;
	private Object content;
	private static final AtomicLong aid = new AtomicLong(1);
	private String command;// 某一个类的某一个方法

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public ClientRequest() {
		id = aid.incrementAndGet();
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Long getId() {
		return id;
	}

}
