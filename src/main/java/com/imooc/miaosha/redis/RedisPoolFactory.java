package com.imooc.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Service
public class RedisPoolFactory {

	@Autowired
	RedisConfig redisConfig;
	//会自动注入到redisService
	@Bean
	public JedisPool JedisPoolFactory() {

		JedisPoolConfig poolConfig = new JedisPoolConfig();

		poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());

		poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());

		poolConfig.setMaxWaitMillis(redisConfig.getPoolMaxWait() * 1000);

		JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(),
				redisConfig.getPort(), redisConfig.getTimeout() * 1000,
				redisConfig.getPassword(), 0);//最后一个参数，redis支持多个库 最多16个， 0 表示从第一个开始
		return jp;
	}

}
