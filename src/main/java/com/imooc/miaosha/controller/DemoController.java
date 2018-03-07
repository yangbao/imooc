package com.imooc.miaosha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.rabbitmq.MQSender;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.redis.UserKey;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.result.ReturnResult;
import com.imooc.miaosha.service.UserService;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@Autowired
	UserService userService;
	@Autowired
	RedisService redisService;
	@Autowired
	MQSender sender;
	
	@RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World!。。。";
    }
	@RequestMapping("/returnSuccess")
    @ResponseBody
    public ReturnResult<String> returnSuccess() {
		return ReturnResult.success("Hello Paul~");
    }
	@RequestMapping("/returnError")
	@ResponseBody
	public ReturnResult<String> returnFilure() {
		return ReturnResult.retrunFailure(CodeMsg.SERVER_ERROR);
	}
	/**
	 * @ResponseBody：该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
	 *  使用时机： 返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用；
	 * @param model
	 * @return
	 */
	@RequestMapping("/returnThymeleaf")
	public String returnThymeleaf(Model model) {
		model.addAttribute("name","Paul");
		return "hello";
	}
	/**
	 * 测试mybatis
	 */
	@RequestMapping("/testMybatis/getUserById")
    @ResponseBody
    public ReturnResult<User> getUserById() {
		User user = userService.getUserById(1);
//		System.out.println(user.getUserId()+"--"+user.getName());
		return ReturnResult.success(user);
    }
	@RequestMapping("/testMybatis/testTx")
    @ResponseBody
    public ReturnResult<Boolean> testTx() {
		boolean flag = userService.testTx();
		return ReturnResult.success(flag);
    }
	/**
	 * 测试redis
	 */
	@RequestMapping("/redis/get")
    @ResponseBody
    public ReturnResult<Long> testJedis() {
		Long result = redisService.get(UserKey.getByName, "", Long.class);
		System.out.println(result);
		return ReturnResult.success(result);
    }
	@RequestMapping("/redis/set")
    @ResponseBody
    public ReturnResult<Boolean> testJedisSet() {
		Boolean result = redisService.set(UserKey.getById,"" ,"id1002");
		return ReturnResult.success(result);
    }
	@RequestMapping("/redis/getUser")
    @ResponseBody
    public ReturnResult<User> redisGet() {
    	User  user  = redisService.get(UserKey.getById, ""+1, User.class);
        return ReturnResult.success(user);
    }
    
    @RequestMapping("/redis/setUser")
    @ResponseBody
    public ReturnResult<Boolean> redisSet() {
    	User user  = new User();
    	user.setUserId(1);
    	user.setName("1111");
    	redisService.set(UserKey.getById, ""+1, user);//UserKey:id1
        return ReturnResult.success(true);
    }
    /*测试rabbit MQ*/
	/*@RequestMapping("/mq")
	@ResponseBody
	public Result<String> mq() {
		sender.send("hello,imooc...");
	return Result.success("Hello，world");
	}
	@RequestMapping("/mq/topic")
   @ResponseBody
   public Result<String> topic() {
		sender.sendTopic("hello,imooc");
      return Result.success("Hello，world");
   }
	
	@RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout() {
		sender.sendFanout("hello,imooc");
        return Result.success("Hello，world");
    }
	
	@RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header() {
		sender.sendHeader("only to key1");
        return Result.success("Hello，world");
    }	
	*/
	
	
}
