package com.imooc.miaosha.service;


import javax.servlet.http.HttpServletResponse;

import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.vo.LoginVo;


public interface UserService {
	
	public User getUserById(Integer userId);

	public boolean testTx();

	public void login(HttpServletResponse response, LoginVo loginVo);
}
