package com.saas.framework;

import java.util.HashMap;

import lombok.Data;

@Data
public class RestEntity {

	public static final int SUCCESS = 200;
	public static final int FAIL = 400;
	public static final int ERROR = 500;

	public static final String INFO_SUCCESS = "操作成功";
	public static final String INFO_FAIL = "操作失败";
	public static final String INFO_ERROR = "网络异常好像奔溃了...";

	private String info = INFO_SUCCESS;
	private Integer code = SUCCESS;
	private Object data = new HashMap<Object, Object>();

	public RestEntity() {

	}

	public static RestEntity SUCCESS() {
		return build();
	}

	public static RestEntity FAIL() {
		return build().code(400).info("操作失败");
	}

	public static RestEntity ERROR() {
		return build().code(500).info("系统异常");
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
