package com.xtwy.client.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.xtwy.client.param.ClientRequest;
import com.xtwy.client.param.Response;

public class DefaultFuture {
	public final static ConcurrentHashMap<Long, DefaultFuture> allDefaultFuture = new ConcurrentHashMap<Long, DefaultFuture>();
	
	final Lock lock = new ReentrantLock();
	
	private Condition condition = lock.newCondition();
	
	private Response response;

	public DefaultFuture(ClientRequest request) {
		allDefaultFuture.put(request.getId(), this);
	}

	//主线程获取数据，首先要等待结果
	public Response get() {
		lock.lock();
		try {
			while(!done()) {
				condition.await(10,TimeUnit.SECONDS);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		return this.response;

	}
	
	public static void receive(Response response) {
		DefaultFuture df = allDefaultFuture.get(response.getId());
		if(df != null) {
			Lock lock = df.lock;
			lock.lock();
			try {
				df.setResponse(response);
				df.condition.signal();
				allDefaultFuture.remove(response.getId());
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				lock.unlock();
			}
		}
		
		
		
	}

	private boolean done() {
		if(this.response != null) {
			return true;
		}
		return false;
	}

	public Response getResponse() {
		return response;
	}

	public void setResponse(Response response) {
		this.response = response;
	}
}
