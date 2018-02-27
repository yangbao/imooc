package com.imooc.miaosha.interceptor;

/**
 * 
 * 项目名称：---
 * 模块名称：接入层
 * 功能描述：异常类
 * 创建人： mao2080@sina.com
 * 创建时间：2017年5月9日 下午8:22:21
 * 修改人： mao2080@sina.com
 * 修改时间：2017年5月9日 下午8:22:21
 */
public class BusinessException extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6922611911789319648L;

	public BusinessException() {
        
    }

    public BusinessException(String message) {
         super(message);
    }
    
}

