package com.dchb.redis;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@Component
//@EnableCaching
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
	@Resource
	private RedisTemplate<String, String> redisTemplate;
	
	@Resource
	private StringRedisTemplate stringRedisTemplate;
	@Cacheable
	public String add(){
		
		redisTemplate.opsForValue().set("key1", "123");
		return redisTemplate.opsForValue().get("key1");
	}
	
	@Test
	public void get(){
		redisTemplate.delete("key1");
		String string = redisTemplate.opsForValue().get("key1");
		System.out.println(string);
		
	}
	
	@Test
	public void testAdd(){
//		add();
		get();
	}
	
	
}
