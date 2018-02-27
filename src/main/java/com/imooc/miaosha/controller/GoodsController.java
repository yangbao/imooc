package com.imooc.miaosha.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.interceptor.AuthValidate;
import com.imooc.miaosha.redis.GoodsKey;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.result.Result;
import com.imooc.miaosha.service.GoodsService;
//import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.service.MiaoshaUserService;
import com.imooc.miaosha.vo.GoodsDetailVo;
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
	//thymeleaf 的页面渲染器, spring 持有这个对象
	@Autowired
	ThymeleafViewResolver thymeleafViewResolver;
	//手动渲染页面需要applicationContext, spring 持有这个对象
	@Autowired
	ApplicationContext applicationContext;
	/**
	 * @CookieValue(value=MiaoshaUserService.COOKI_NAME_TOKEN,required=false)String cookieToken,
	 * @RequestParam(value=MiaoshaUserService.COOKI_NAME_TOKEN,required=false)String paramToken
	 * 这2个参数很烦， 多个方法都需要他们，然后判断。使用自定义数据绑定---HandlerMethodArgumentResolver
	 */
	/**
	 * 5000 * 10
	 * QPS:1267 load:15 mysql
	 * 用页面缓存之后的QPS
	 * QPS:2884, load:5 
	 * */
    @RequestMapping("/to_list")
    //用来做静态页面的缓存,返回的不再是template名字,而是html的字串, 需要去手动渲染
    @ResponseBody
//    @AuthValidate
    public String list(Model model,MiaoshaUser user,
    		HttpServletRequest request, HttpServletResponse response // 这2个参数是手动渲染页面需要的
    		//做了优化，用自定义的参数处理器，详见config包
//    		HttpServletResponse response,
//    		@CookieValue(value=MiaoshaUserService.COOKI_NAME_TOKEN,required=false)String cookieToken,
//    		@RequestParam(value=MiaoshaUserService.COOKI_NAME_TOKEN,required=false)String paramToken
    		) {
/*    	
 		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)){
    		return "to_login";
    	}
    	String token = StringUtils.isNotEmpty(paramToken) ? paramToken : cookieToken;
    	user = userService.getByToken(response, token);
    	user = redisService.get(MiaoshaUserKey.token, token, MiaoshaUser.class);
*/
    	model.addAttribute("user", user);
    	//取静态页面的缓存
    	String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
    	if(StringUtils.isNotBlank(html)){
    		return html;
    	}
    	//否则就手动渲染,然后存入缓存
    	//查询商品列表
    	List<GoodsVo> goodsList = goodsService.listGoodsVo();
    	model.addAttribute("goodsList", goodsList);
    	SpringWebContext ctx = new SpringWebContext(request,response,
    			request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
    	
    	html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
    	if(!StringUtils.isEmpty(html)) {
    		redisService.set(GoodsKey.getGoodsList, "", html);
    	}
    	return html;
//        return "goods_list";
    }
    /*使用页面静态化来优化*/
    @RequestMapping("/detail/{goodsId}")
    @ResponseBody
    public Result<GoodsDetailVo> detail(Model model,MiaoshaUser user,
    		@PathVariable("goodsId")long goodsId,
    		HttpServletRequest request, HttpServletResponse response // 这2个参数是手动渲染页面需要的
    		) {
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	long startAt = goods.getStartDate().getTime();
    	long endAt = goods.getEndDate().getTime();
    	long now = System.currentTimeMillis();
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {//秒杀还没开始，倒计时
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){//秒杀已经结束
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	GoodsDetailVo vo = new GoodsDetailVo();
    	vo.setGoods(goods);
    	vo.setUser(user);
    	vo.setRemainSeconds(remainSeconds);
    	vo.setMiaoshaStatus(miaoshaStatus);
    	return Result.success(vo);
    }
    //一般不会用自增长的ID作为参数，因为很容易被别人爬虫，遍历你的库，也不用UUID 而会用snowflake算法
    @RequestMapping(value="/to_detail2/{goodsId}",produces="text/html")
    @ResponseBody
    public String detail2(Model model,MiaoshaUser user,
    		@PathVariable("goodsId")long goodsId,
    		HttpServletRequest request, HttpServletResponse response // 这2个参数是手动渲染页面需要的
    		) {
    	model.addAttribute("user", user);
    	
    	//取静态页面的缓存
    	String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
    	if(StringUtils.isNotBlank(html)){
    		return html;
    	}
    	//手动渲染
    	
    	GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	model.addAttribute("goods", goods);
    	
    	long startAt = goods.getStartDate().getTime();
    	long endAt = goods.getEndDate().getTime();
    	long now = System.currentTimeMillis();
    	
    	int miaoshaStatus = 0;
    	int remainSeconds = 0;
    	if(now < startAt ) {//秒杀还没开始，倒计时
    		miaoshaStatus = 0;
    		remainSeconds = (int)((startAt - now )/1000);
    	}else  if(now > endAt){//秒杀已经结束
    		miaoshaStatus = 2;
    		remainSeconds = -1;
    	}else {//秒杀进行中
    		miaoshaStatus = 1;
    		remainSeconds = 0;
    	}
    	model.addAttribute("miaoshaStatus", miaoshaStatus);
    	model.addAttribute("remainSeconds", remainSeconds);
    	
    	SpringWebContext ctx = new SpringWebContext(request,response,
    			request.getServletContext(),request.getLocale(), model.asMap(), applicationContext );
    	
    	html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
    	if(!StringUtils.isEmpty(html)) {
    		redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
    	}
    	return html;
    	
//        return "goods_detail";
    }
    
}
