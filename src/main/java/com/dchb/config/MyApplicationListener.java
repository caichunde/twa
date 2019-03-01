package com.dchb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

@Configuration
@WebListener
public class MyApplicationListener implements ServletContextListener {
    private Logger logger =  LoggerFactory.getLogger(MyApplicationListener.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("liting: contextInitialized");
        System.err.println("MyApplicationListener初始化成功");
        ServletContext context = sce.getServletContext();
        // IP存储器
        Map<String, Long[]> ipMap = new HashMap();
        context.setAttribute("ipMap", ipMap);
        // 限制IP存储器：存储被限制的IP信息
        Map<String, Long> limitedIpMap = new HashMap();
        context.setAttribute("limitedIpMap", limitedIpMap);
        logger.info("ipmap："+ipMap.toString()+";limitedIpMap:"+limitedIpMap.toString()+"初始化成功。。。。。");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // TODO Auto-generated method stub

    }
}
