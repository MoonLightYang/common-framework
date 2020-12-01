package com.saas.framework.session;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionUser {

	private String token;
	private String enterprise;
	private String handler;
	private Integer enterpriseId;
	private String account;
	private Integer handlerNo;

	public SessionUser(String token) {
		this.token = token;
	}

}
