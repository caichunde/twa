package com.dchb.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

/**
 * @Title: 所有请求参数统一使用过滤器解析处理
 * @Description:
 * @Author: caichunde
 * @Date: 2019-1-23
 */
@Component
public class ControllerFileter implements Filter {
    private Logger logger = LoggerFactory.getLogger(ControllerFileter.class);
    // 排除的路径
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/downloadFile", "/downloadTranReport", "/pttLogin")));
    /**
     * 默认限制时间（单位：ms）
     */
    private static final long LIMITED_TIME_MILLIS = 60 * 60 * 1000;

    /**
     * 用户连续访问最高阀值，超过该值则认定为恶意操作的IP，进行限制
     */
    private static final int LIMIT_NUMBER = 10;

    /**
     * 用户访问最小安全时间，在该时间内如果访问次数大于阀值，则记录为恶意IP，否则视为正常访问
     */
    private static final int MIN_SAFE_TIME = 1000;
    private FilterConfig config;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.config = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        ServletContext context = config.getServletContext();
        // 获取限制IP存储器：存储被限制的IP信息
        Map<String, Long> limitedIpMap = (Map<String, Long>) context.getAttribute("limitedIpMap");
        // 过滤受限的IP
        filterLimitedIpMap(limitedIpMap);
        // 获取用户IP
        String ip = httpRequest.getRemoteAddr();
        // 判断是否是被限制的IP，如果是则跳到异常页面
        if (isLimitedIP(limitedIpMap, ip)) {
            long limitedTime = limitedIpMap.get(ip) - System.currentTimeMillis();
            // 剩余限制时间(用为从毫秒到秒转化的一定会存在些许误差，但基本可以忽略不计)
            httpRequest.setAttribute("remainingTime", ((limitedTime / 1000) + (limitedTime % 1000 > 0 ? 1 : 0)));
            System.err.println("ip访问过于频繁，（CC）攻击嫌疑--攻击ip：" + ip);
            logger.info("ip访问过于频繁，（CC）攻击嫌疑--攻击ip：" + ip);
            return;
        }
        // 获取IP存储器
        Map<String, Long[]> ipMap = (Map<String, Long[]>) context.getAttribute("ipMap");
        // 判断存储器中是否存在当前IP，如果没有则为初次访问，初始化该ip
        // 如果存在当前ip，则验证当前ip的访问次数
        // 如果大于限制阀值，判断达到阀值的时间，如果不大于[用户访问最小安全时间]则视为恶意访问，跳转到异常页面
        if (ipMap.containsKey(ip)) {
            Long[] ipInfo = ipMap.get(ip);
            ipInfo[0] = ipInfo[0] + 1;
            if (ipInfo[0] > LIMIT_NUMBER) {
                Long ipAccessTime = ipInfo[1];
                Long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - ipAccessTime <= MIN_SAFE_TIME) {
                    limitedIpMap.put(ip, currentTimeMillis + LIMITED_TIME_MILLIS);
                    httpRequest.setAttribute("remainingTime", LIMITED_TIME_MILLIS);
                    System.err.println("ip访问过于频繁，（CC）攻击嫌疑--攻击ip：" + ip);
                    logger.info("ip访问过于频繁，（CC）攻击嫌疑--攻击ip：" + ip);
                    return;
                } else {
                    initIpVisitsNumber(ipMap, ip);
                }
            }
        } else {
            initIpVisitsNumber(ipMap, ip);
        }
        context.setAttribute("ipMap", ipMap);

        String path = httpRequest.getRequestURI();
        filterChain.doFilter(new ParamsRequestWrapper(httpRequest, excludePath(path)), servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * @param limitedIpMap
     * @Description 过滤受限的IP，剔除已经到期的限制IP
     */
    private void filterLimitedIpMap(Map<String, Long> limitedIpMap) {
        if (limitedIpMap == null) {
            return;
        }
        Set<String> keys = limitedIpMap.keySet();
        Iterator<String> keyIt = keys.iterator();
        long currentTimeMillis = System.currentTimeMillis();
        while (keyIt.hasNext()) {
            long expireTimeMillis = limitedIpMap.get(keyIt.next());
            if (expireTimeMillis <= currentTimeMillis) {
                keyIt.remove();
            }
        }
    }

    /**
     * @param limitedIpMap
     * @param ip
     * @return true : 被限制 | false : 正常
     * @Description 是否是被限制的IP
     */
    private boolean isLimitedIP(Map<String, Long> limitedIpMap, String ip) {
        if (limitedIpMap == null || ip == null) {
            // 没有被限制
            return false;
        }
        Set<String> keys = limitedIpMap.keySet();
        Iterator<String> keyIt = keys.iterator();
        while (keyIt.hasNext()) {
            String key = keyIt.next();
            if (key.equals(ip)) {
                // 被限制的IP
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化用户访问次数和访问时间
     *
     * @param ipMap
     * @param ip
     */
    private void initIpVisitsNumber(Map<String, Long[]> ipMap, String ip) {
        Long[] ipInfo = new Long[2];
        ipInfo[0] = 0L;// 访问次数
        ipInfo[1] = System.currentTimeMillis();// 初次访问时间
        ipMap.put(ip, ipInfo);
    }


    // 判断排除不解密的路径
    public boolean excludePath(String requestURI) {
        Iterator iterator = this.ALLOWED_PATHS.iterator();
        while (iterator.hasNext()) {
            String path = (String) iterator.next();
            if (requestURI.contains(path)) {
                return false;
            }
        }
        return true;
    }
}
