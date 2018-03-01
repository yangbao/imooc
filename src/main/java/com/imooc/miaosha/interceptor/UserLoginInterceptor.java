package com.imooc.miaosha.interceptor;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.imooc.miaosha.domain.MiaoshaUser;
import com.imooc.miaosha.exception.GlobalException;
import com.imooc.miaosha.result.CodeMsg;
import com.imooc.miaosha.service.MiaoshaUserService;


/**
 * 
 * 项目名称：---
 * 模块名称：接入层
 * 功能描述：用户登录拦截器（利用SpringMVC自定义拦截器实现）
 * 创建人： mao2080@sina.com
 * 创建时间：2017年4月25日 下午8:53:49
 * 修改人： mao2080@sina.com
 * 修改时间：2017年4月25日 下午8:53:49
 */
@Service
public class UserLoginInterceptor implements HandlerInterceptor {
	
	@Autowired
	MiaoshaUserService userService;
    
    /**
     * 
     * 描述：构造函数
     * @author mao2080@sina.com
     * @created 2017年4月28日 下午5:20:34
     * @since 
     * @param accessService
     */
    public UserLoginInterceptor() {
        
    }

    /**
     * 
     * 描述：执行方法前
     * @author mao2080@sina.com
     * @created 2017年4月25日 下午9:01:44
     * @since 
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @param handler handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            //校验登录
//            this.userLoginValidate(request);
            //校验权限
//            this.userAuthValidate(request, handler);
        } catch (Exception e) {
            e.printStackTrace();
            printMessage(response, e);
            return false;
        }
        if(!userLoginValidate(request,response,handler)){
        	response.sendRedirect("/login/to_login");
        }
        return true;
    }
    
    /**
     * 
     * 描述：输出到前端
     * @author mao2080@sina.com
     * @created 2017年4月28日 上午11:00:25
     * @since 
     * @param response 响应
     * @param res 对象
     * @throws Exception
     */
    public static void printMessage(HttpServletResponse response, Object res) throws Exception{
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=utf-8");
        try {
            writer = response.getWriter();
            writer.print(JsonUtil.toJson(res));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null){
                writer.close();
            }
        }
    }
    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        
    }

    @Override
    public void afterCompletion(HttpServletRequest request,    HttpServletResponse response, Object handler, Exception ex)    throws Exception {
        
    }
    
    /**
     * 
     * 描述：用户登录校验
     * @author mao2080@sina.com
     * @created 2017年5月9日 下午8:27:25
     * @since 
     * @param request
     * @throws BusinessException
     */
    private boolean userLoginValidate(HttpServletRequest request,HttpServletResponse response,Object handler) throws BusinessException {
    	
    	 AuthValidate validate = ((HandlerMethod) handler).getMethodAnnotation(AuthValidate.class);
         if(validate == null){
//             throw new BusinessException("未配置自定义注解");
         }
    	String paramToken = request.getParameter(MiaoshaUserService.COOKI_NAME_TOKEN);
		String cookieToken = getCookieValue(request, MiaoshaUserService.COOKI_NAME_TOKEN);
		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//			throw new GlobalException(CodeMsg.SESSION_ERROR); 
			return false;
		}
		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
//		userService = new MiaoshaUserService();
		MiaoshaUser user = userService.getByToken(response, token);
		if(user == null)	{
			return false;
//			throw new GlobalException(CodeMsg.SESSION_ERROR); 
		}
		return true;
    }
    private String getCookieValue(HttpServletRequest request, String cookiName) {
		Cookie[]  cookies = request.getCookies();
		
		if(cookies == null || cookies.length <= 0){
			return null;
		}
		for(Cookie cookie : cookies) {
			if(cookie.getName().equals(cookiName)) {
				return cookie.getValue();
			}
		}
		return null;
	}
    /**
     * 
     * 描述：用户权限校验
     * @author mao2080@sina.com
     * @created 2017年5月4日 下午8:34:09
     * @since 
     * @param request HttpServletRequest
     * @param handler 
     * @return
     * @throws BusinessException
     */
    private void userAuthValidate(HttpServletRequest request, Object handler) throws BusinessException {
        AuthValidate validate = ((HandlerMethod) handler).getMethodAnnotation(AuthValidate.class);
        if(validate == null){
            throw new BusinessException("未配置自定义注解");
        }
        String funcCode = validate.value().getAuthCode();
        if(funcCode.equals(AuthCode.Allow.getAuthCode())){
            return;
        }
        String authId = validate.value().getAuthId();
        List<String> auths = new ArrayList<>();//模拟从缓存或者从数据库中查询出对应用户的权限
        if(!auths.contains(authId)){
            throw new BusinessException("权限不足");
        }
    }

}