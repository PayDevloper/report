package com.sumao.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * spring工具类
 * 
 * @author 陈小俊
 * 
 */
public class SpringUtil {

	private static final ApplicationContext ac = new ClassPathXmlApplicationContext("classpath*:spring-mvc.xml");

	public static ApplicationContext getApplicationContext() {
		return ac;
	}

	public static Object getBean(String beanName) {
		return ac.getBean(beanName);
	}

}
