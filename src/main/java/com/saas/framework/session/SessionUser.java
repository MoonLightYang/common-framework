package com.saas.framework.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionUser {

	private String token;
	private Integer enterpriseId;
	private String enterprise;
	private String account;
	private String handler;
	private Integer handlerNo;

	public SessionUser(String token) {
		this.token = token;
	}

}
