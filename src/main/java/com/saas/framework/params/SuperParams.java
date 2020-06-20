package com.saas.framework.params;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SuperParams {

	@Getter
	@Setter
	public String handler;

	@Getter
	@Setter
	public Integer handlerNo;

	@Setter
	@Getter
	public String enterprise;

	@Setter
	@Getter
	public Integer enterpriseId;

	@Getter
	@Setter
	public String ip;

	@Setter
	@Getter
	public String token;

}
