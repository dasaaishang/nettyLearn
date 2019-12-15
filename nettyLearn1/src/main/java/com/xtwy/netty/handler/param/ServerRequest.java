package com.xtwy.netty.handler.param;

public class ServerRequest {
private Long id;
private Object content;
private String command;//某一个类的某一个方法
public String getCommand() {
	return command;
}
public void setCommand(String command) {
	this.command = command;
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public Object getContent() {
	return content;
}
public void setContent(Object content) {
	this.content = content;
}
}
