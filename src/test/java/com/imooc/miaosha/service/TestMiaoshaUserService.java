package com.imooc.miaosha.service;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


import com.imooc.miaosha.MainApplication;

//SpringJUnit支持，由此引入Spring-Test框架支持！
@RunWith(SpringJUnit4ClassRunner.class)
//指定我们SpringBoot工程的Application启动类
@SpringBootTest(classes = MainApplication.class)
//由于是Web项目，Junit需要模拟ServletContext，因此我们需要给我们的测试类加上@WebAppConfiguration。
@WebAppConfiguration
public class TestMiaoshaUserService {
	
	@Autowired
	MiaoshaUserService miaoshaUserService;
	@Autowired
    private HelloService helloService;
	@Before
	 public void setup() {
//		jedis = new Jedis("10.35.47.53",6379);
//		jedis.auth("108512");
//		jedis.connect();
	 }
	@Test
	public void testUpdate() {
//		Assert.assertEquals("hello",helloService.getName());
		miaoshaUserService.updatePassword("9941877576d54657b523d46144d7709c", 18801447856L, "bgkp108512");
	}
	@After
	 public void tearDown() {
//		if(jedis!=null){
//			jedis.close();
//		}
	 }
}
