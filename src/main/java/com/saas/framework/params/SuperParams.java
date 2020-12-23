package com.saas.framework.params;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class SuperParams {

	@Getter
	@Setter
	protected String handler;

	@Getter
	@Setter
	protected Integer handlerNo;

	@Setter
	@Getter
	protected String enterprise;

	@Setter
	@Getter
	protected Integer enterpriseId;

	@Getter
	@Setter
	protected String ip;

	@Setter
	@Getter
	protected String token;
	
	@Setter
	@Getter
	protected Integer source;

}
