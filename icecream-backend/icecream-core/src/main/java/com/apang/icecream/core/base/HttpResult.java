package com.apang.icecream.core.base;

/**
 * http请求结果封装
 *
 * @author andy.pang
 * @since 2019-09-06
 */
public class HttpResult {

	private int code = 200;
	private String msg;
	private Object data;
	private boolean success = true;
	
	public static HttpResult error() {
		return error(200, "未知异常，请联系管理员");
	}

	public static HttpResult error(String msg) {
		return error(200, msg);
	}
	
	public static HttpResult error(int code, String msg) {
		HttpResult r = new HttpResult();
		r.setCode(code);
		r.setMsg(msg);
		r.setSuccess(false);
		return r;
	}

	public static HttpResult ok(String msg) {
		HttpResult r = new HttpResult();
		r.setMsg(msg);
		r.setSuccess(true);
		return r;
	}
	
	public static HttpResult ok(Object data) {
		HttpResult r = new HttpResult();
		r.setData(data);
		r.setSuccess(true);
		return r;
	}
	
	public static HttpResult ok() {
		return new HttpResult();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
}
