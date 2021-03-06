package com.imooc.miaosha.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.imooc.miaosha.access.UserContext;
import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.service.MiaoshaUserService;

/**
 * 自定义数据绑定---HandlerMethodArgumentResolver
 * @author u6035457
 */
@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	MiaoshaUserService userService;
	
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz = parameter.getParameterType();
		return clazz==MiaoshaUser.class;
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		/*HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		
		String paramToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
		String cookieToken = getCookieValue(request, MiaoshaUserService.COOKI_NAME_TOKEN);
		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
		return userService.getByToken(response, token);*/
		//放到拦截器里面获取了, 上面的代码 ThreadLocal当作了传参的作用. 从interceptor到了HandlerMethodArgumentResolver
		return UserContext.getUser();
	}
	//可以放到localStorge吗?
//	private String getCookieValue(HttpServletRequest request, String cookiName) {
//		Cookie[]  cookies = request.getCookies();
//		
//		if(cookies == null || cookies.length <= 0){
//			return null;
//		}
//		for(Cookie cookie : cookies) {
//			if(cookie.getName().equals(cookiName)) {
//				return cookie.getValue();
//			}
//		}
//		return null;
//	}

}
