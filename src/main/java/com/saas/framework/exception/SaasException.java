package com.saas.framework.exception;

public class SaasException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SaasException(String field, Object obj) {
		super("[" + field + "]" + obj.toString());
	}

	public SaasException() {

	}

	public SaasException(String msg) {
		super(msg);
	}

}
