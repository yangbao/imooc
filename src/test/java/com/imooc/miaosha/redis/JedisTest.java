package com.imooc.miaosha.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;

public class JedisTest {

	private Jedis jedis; 
	
	@Before
	 public void setup() {
		jedis = new Jedis("10.35.47.53",6379);
		jedis.auth("108512");
		jedis.connect();
	 }
	@Test
	public void testString() {
		//-----添加数据----------  
//		jedis.set("name","中文");//向key-->name中放入了value-->xinxin  
//		System.out.println(jedis.get("name"));//执行结果：xinxin  
//		jedis.append("name", " is my lover"); //拼接
//		System.out.println(jedis.get("name")); 
//		jedis.del("name");  //删除某个键
//		System.out.println(jedis.get("name"));
//		//设置多个键值对
		jedis.mset("name","liuling","age","23","qq","476777XXX");
		System.out.println(jedis.get("name") + "-" + jedis.get("age") + "-" + jedis.get("qq"));
//		jedis.incr("age"); //进行加1操作
	}
	@After
	 public void tearDown() {
		if(jedis!=null){
			jedis.close();
		}
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
