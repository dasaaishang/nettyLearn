package com.xtwy.netty.medium;

import java.lang.reflect.Method;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import com.xtwy.netty.annotation.Remote;

@Component
public class InitialMedium implements BeanPostProcessor{
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if(bean.getClass().isAnnotationPresent(Remote.class)) {
			Method[] methods = bean.getClass().getDeclaredMethods();
			for (Method m : methods) {
				String key = bean.getClass().getInterfaces()[0].getName()+"."+m.getName();
				BeanMethod beanMethod = new BeanMethod();
				beanMethod.setBean(bean);
				beanMethod.setMethod(m);
				Media.beanMap.put(key, beanMethod);
			}
		}
		return bean;
	}

}
