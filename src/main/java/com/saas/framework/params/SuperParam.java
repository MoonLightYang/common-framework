package com.saas.framework.params;

import lombok.Getter;
import lombok.Setter;

public class SuperParam {

	@Getter
	@Setter
	public String handler; // 操作人编号

	@Getter
	@Setter
	public Integer handlerNo; // 操作人编号

	@Getter
	@Setter
	public String ip; // ip 地址

	@Setter
	@Getter
	public Integer enterpriseId; // 公司Id

	@Setter
	@Getter
	public String token;

	public SuperParam() {
	}

}
