package com.imooc.miaosha.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.redis.MiaoshaUserKey;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.service.GoodsService;
//import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaUserService;
//import com.imooc.miaosha.vo.GoodsVo;
import com.imooc.miaosha.vo.GoodsVo;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	MiaoshaUserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
//	
    @RequestMapping("/to_list")
    public String list(Model model,MiaoshaUser user,
    		@CookieValue(value=MiaoshaUserService.COOKI_NAME_TOKEN,required=false)String cookieToken,
    		@RequestParam(value=MiaoshaUserService.COOKI_NAME_TOKEN,required=false)String paramToken
    		) {
    	if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
    		return "to_login";
    	}
    	String key = StringUtils.isNotEmpty(paramToken) ? paramToken : cookieToken;
    	user = redisService.get(MiaoshaUserKey.token, key, MiaoshaUser.class);
    	System.out.println(user);
    	model.addAttribute("user", user);
    	//查询商品列表
//    	List<GoodsVo> goodsList = goodsService.listGoodsVo();
//    	model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }
    
//    @RequestMapping("/to_detail/{goodsId}")
//    public String detail(Model model,MiaoshaUser user,
//    		@PathVariable("goodsId")long goodsId) {
//    	model.addAttribute("user", user);
//    	
//    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
//    	model.addAttribute("goods", goods);
//    	
//    	long startAt = goods.getStartDate().getTime();
//    	long endAt = goods.getEndDate().getTime();
//    	long now = System.currentTimeMillis();
//    	
//    	int miaoshaStatus = 0;
//    	int remainSeconds = 0;
//    	if(now < startAt ) {//秒杀还没开始，倒计时
//    		miaoshaStatus = 0;
//    		remainSeconds = (int)((startAt - now )/1000);
//    	}else  if(now > endAt){//秒杀已经结束
//    		miaoshaStatus = 2;
//    		remainSeconds = -1;
//    	}else {//秒杀进行中
//    		miaoshaStatus = 1;
//    		remainSeconds = 0;
//    	}
//    	model.addAttribute("miaoshaStatus", miaoshaStatus);
//    	model.addAttribute("remainSeconds", remainSeconds);
//        return "goods_detail";
//    }
    
}
