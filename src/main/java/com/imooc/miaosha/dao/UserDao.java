package com.imooc.miaosha.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.imooc.miaosha.domain.User;

@Mapper
public interface UserDao {
	//不需要实现类
	@Select("select * from user where id = #{userId}")
	@Results({
		@Result(property = "userId",  column = "id")
	})
	public User selectUserById(@Param("userId")Integer userId);
	@Insert("insert into user (id,name) values (#{userId},#{name})")
	public void insetUser(User u);
}
