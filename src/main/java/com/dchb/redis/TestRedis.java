/**
	 * 
	 * @Title: ()   
	 * @Description: TODO()    
	 * @param @param 
	 * @return ReturnMsg    返回类型 
	 * @author hukai
	 * @date 2018年9月21日
	 */
package com.dchb.redis;

import redis.clients.jedis.Jedis;

/**
 * 
 * @Title: ()   
 * @Description: TODO()    
 * @param @param 
 * @return ReturnMsg    返回类型 
 * @author hukai
 * @date date上午11:12:40
 */
public class TestRedis {
	private static Jedis jedis;

	public static void main(String[] args) {
		 //连接本地的 Redis 服务
        //Jedis jedis = new Jedis("localhost");
        jedis = new Jedis("192.168.0.134",6379);

        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
		
	}
}
