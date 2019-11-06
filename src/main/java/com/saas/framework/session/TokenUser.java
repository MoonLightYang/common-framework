package com.saas.framework.session;

import lombok.Data;

@Data
public class TokenUser {

	private Integer enterpriseId;
	private String enterprise;
	private String account;
	private String handler;
	private Integer handlerNo;
	private String ipAddress;
	private String token;

}
