package com.imooc.miaosha.config;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.imooc.miaosha.redis.GoodsKey;
import com.imooc.miaosha.redis.RedisService;
import com.imooc.miaosha.service.GoodsService;
import com.imooc.miaosha.vo.GoodsStockVo;

//@Component
//public class LaunchLoader implements CommandLineRunner{
//
//	@Autowired
//	RedisService redisService;
//	
//	@Autowired
//	GoodsService goodsService;
//	/**
//	 * 系统初始化的时候,把商品库存数量加载到Redis, 为预减库存做准备
//	 */
//	@Override
//	public void run(String... arg0) throws Exception {
//		System.out.println(arg0);
//		List<GoodsStockVo> goodsStrockVo = goodsService.listGoodsStockVo();
//		if(goodsStrockVo != null && goodsStrockVo.size()>0){
//			for (GoodsStockVo vo : goodsStrockVo) {
//				redisService.set(GoodsKey.goods_stock_key, ""+vo.getId(), vo.getStockCount()+"");
//			}
//		}
//		System.out.println("save stocks end ...");
//	}
//
//}
