package com.imooc.miaosha.access;

import com.imooc.miaosha.domain.MiaoshaUser;

public class UserContext {
	/**
	 * 跟当前线程绑定的, 放到当前线程里面.
	 * 使用场景:　如果一个对象要被多个线程访问，而该对象存在类变量被不同类方法读写，为获得线程安全，可以用ThreadLocal来替代类变量。
	 */
	private static ThreadLocal<MiaoshaUser> userHolder = new ThreadLocal<MiaoshaUser>();
	
	public static void setUser(MiaoshaUser user) {
		userHolder.set(user);
	}
	
	public static MiaoshaUser getUser() {
		return userHolder.get();
	}

}
