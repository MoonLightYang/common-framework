package com.saas.framework.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenUser {

	private Integer enterpriseId;
	private String enterprise;
	private String account;
	private String handler;
	private Integer handlerNo;
	private String ipAddress;
	private String token;

}
