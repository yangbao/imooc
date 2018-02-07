package com.imooc.miaosha.service;


import com.imooc.miaosha.entity.User;


public interface UserService {
	
	public User getUserById(Integer userId);

	public boolean testTx();
}
