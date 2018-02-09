package com.imooc.miaosha.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.miaosha.dao.UserDao;
import com.imooc.miaosha.domain.User;
import com.imooc.miaosha.service.UserService;
import com.imooc.miaosha.vo.LoginVo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	@Override
	public User getUserById(Integer id) {
		
		return userDao.selectUserById(id);
	}
	@Override
	@Transactional
	public boolean testTx() {
		User u1 = new User(6,"user6");
		userDao.insetUser(u1);
		User u2 = new User(4,"user4");
		userDao.insetUser(u2);
		return true;
	}
	@Override
	public void login(HttpServletResponse response, LoginVo loginVo) {
		
	}

}
