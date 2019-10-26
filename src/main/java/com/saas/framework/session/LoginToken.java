package com.saas.framework.session;

import lombok.Data;

@Data
public class LoginToken {

	private String role;
	private Integer roleId;
	private String handler;
	private Integer handlerId;
	private Integer enterpriseId;
	private String ipAddress;

}
