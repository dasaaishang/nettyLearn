package com.xtwy.netty.medium;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.xtwy.netty.handler.param.ServerRequest;
import com.xtwy.netty.util.Response;

public class Media {
	private static Media media = new Media();

	public static Map<String, BeanMethod> beanMap;
	static {
		beanMap = new HashMap<String, BeanMethod>();
	}

	private Media() {

	}

	public static Media newInstance() {
		// TODO Auto-generated method stub
		return media;
	}

	// 非常关键,反射处理业务
	public Response process(ServerRequest request) {
		Response result = null;
		try {
			String command = request.getCommand();
			BeanMethod beanMethod = beanMap.get(command);
			if(beanMethod == null) {
				return null;
			}
			Object bean = beanMethod.getBean();
			Method m = beanMethod.getMethod();
			Class paramType = m.getParameterTypes()[0];
			Object content = request.getContent();
			
			
			@SuppressWarnings("unchecked")
			Object args = JSONObject.parseObject(JSONObject.toJSONString(content),paramType);
			
			result = (Response) m.invoke(bean, args);
			result.setId(request.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
