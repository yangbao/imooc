package com.imooc.miaosha.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.miaosha.interceptor.UserLoginInterceptor;

//import com.imooc.miaosha.interceptor.UserLoginInterceptor;

/**
 * 加入自定义的HandlerMethodArgumentResolver
 * @author u6035457
 * WebMvcConfigurer用来做controller的数据绑定。WebMvcConfigurerAdapter 模板提供给用户用来覆盖里面的一些方法。
 */
@Configuration
//@EnableWebMvc	 加上后找不到前台的静态页面 - 会把这个类当成mvc的配置文件使用
public class WebConfig  extends WebMvcConfigurerAdapter{
	
	@Autowired
	UserArgumentResolver userArgumentResolver;
	@Bean
    public UserLoginInterceptor myInterceptor(){
        return new UserLoginInterceptor();
    }
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(userArgumentResolver);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(myInterceptor()).addPathPatterns("/goods/**");
		super.addInterceptors(registry);
//		this.excludeUserLogin(registry.addInterceptor(new UserLoginInterceptor()));
	}
	public void excludeUserLogin(InterceptorRegistration registration){
//	   registration.addPathPatterns("/*");
	}
}
