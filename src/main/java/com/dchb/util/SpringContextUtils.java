package com.dchb.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtils implements ApplicationContextAware{

	private static ApplicationContext applicationContext;
	
	/** 
     * 实现ApplicationContextAware接口的回调方法。设置上下文环境 
     *  
     * @param applicationContext 
     */  
    public void setApplicationContext(ApplicationContext applicationContext) {  
        SpringContextUtils.applicationContext = applicationContext;  
    }  
  
    /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
	
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String beanName){
		return (T)applicationContext.getBean(beanName); 
	}
	
	public static <T> T getBean(Class<T> clazz){
		return applicationContext.getBean(clazz);
	}
}
