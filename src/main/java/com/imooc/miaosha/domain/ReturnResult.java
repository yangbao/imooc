package com.imooc.miaosha.domain;

public class ReturnResult<T> {

	private int code;
	private String msg;
	private T data;
	
	
	public ReturnResult() {
	}

	public ReturnResult(CodeMsg codeMsg) {
		if(codeMsg == null) {
			return;
		}
		this.code = codeMsg.getCode();
		this.msg = codeMsg.getMsg();
	}

	public ReturnResult(T data) {
		this.code = Contant.SUCCESS_CODE;
		this.msg = Contant.SUCCESS_MSG;
		this.data = data;
	}
	/**
	 * 成功时候的调用
	 * */
	public static <T> ReturnResult<T> success(T data){
		return new ReturnResult<T>(data);
	}
	/**
	 * 失败时候的调用
	 * */
	public static <T> ReturnResult<T> retrunFailure(CodeMsg codeMsg){
		return new ReturnResult<T>(codeMsg);
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
	public Object getData() {
		return data;
	}
	
}
