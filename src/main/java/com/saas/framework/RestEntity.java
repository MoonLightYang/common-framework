package com.saas.framework;

import java.util.HashMap;

import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class RestEntity {

	public static final int SUCCESS = 200;
	public static final int FAIL = 400;
	public static final int ERROR = 500;

	public static final String INFO_SUCCESS = "成功";
	public static final String INFO_FAIL = "失败";
	public static final String INFO_ERROR = "网络奔溃了...";

	// ------------------------------------------
	public static final int UNLOGIN = 401;
	public static final int EXPIRE = 402;
	public static final int FORBIDDEN = 403;

	public static final String INFO_UNLOGIN = "用户未登录";
	public static final String INFO_FORBIDDEN = "无权限，禁止访问";
	public static final String INFO_EXPIRE = "登录已过期";

	private String info = INFO_SUCCESS;
	private Integer code = SUCCESS;
	private Object data = new HashMap<Object, Object>();

	public RestEntity() {

	}

	/**
	 * 操作成功
	 * 
	 * @return
	 */
	public static RestEntity SUCCESS() {
		return build();
	}

	/**
	 * 操作失败
	 * 
	 * @return
	 */
	public static RestEntity FAIL() {
		return build().code(FAIL).info(INFO_FAIL);
	}

	/**
	 * 操作失败
	 * 
	 * @return
	 */
	public static RestEntity FAIL(String info) {
		if (StringUtils.isEmpty(info))
			return build().code(FAIL).info(INFO_FAIL);

		return build().code(FAIL).info(info);
	}

	/**
	 * 操作异常
	 * 
	 * @return
	 */
	public static RestEntity ERROR() {
		return build().code(ERROR).info(INFO_ERROR);
	}

	/**
	 * 登陆已失效
	 * 
	 * @return
	 */
	public static RestEntity EXPIRE() {
		return build().code(EXPIRE).info(INFO_EXPIRE);
	}

	/**
	 * 无权限，禁止访问
	 * 
	 * @return
	 */
	public static RestEntity FORBIDDEN() {
		return build().code(FORBIDDEN).info(INFO_FORBIDDEN);
	}

	/**
	 * 用户、未登陆
	 * 
	 * @return
	 */
	public static RestEntity UNLOGIN() {
		return build().code(UNLOGIN).info(INFO_UNLOGIN);
	}

	public static RestEntity build() {
		return new RestEntity();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public RestEntity fastData(String key, Object value) {
		if (this.data instanceof HashMap) {
			((HashMap) data).put(key, value);
		}
		return this;
	}

	public RestEntity code(Integer code) {
		this.code = code;
		return this;
	}

	public RestEntity info(String info) {
		this.info = info;
		return this;
	}

	public RestEntity data(Object data) {
		this.data = data;
		return this;
	}

}
