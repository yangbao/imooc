package com.imooc.miaosha.redis;

public class GoodsKey extends BasePrefix{

	public static GoodsKey getGoodsList = new GoodsKey(60,"goodsList_");
	public static GoodsKey getGoodsDetail = new GoodsKey(60, "goodsDetail_");
	
	public GoodsKey(int expireSeconds, String prefix) {
		
		super(expireSeconds, prefix);
	}

}
