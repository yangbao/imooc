package com.imooc.miaosha.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface AuthValidate {

	/**
	 * 描述：权限定义
	 * @author mao2080@sina.com
	 * @created 2017年5月8日 上午11:36:41
	 * @since
	 * @return 权限代码
	 */
	AuthCode value() default AuthCode.Allow;

}