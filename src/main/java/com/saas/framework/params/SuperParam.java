package com.saas.framework.params;

import com.saas.framework.exception.SaasException;

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
	public String ipAddress; // ip 地址

	@Getter
	public Integer companyId; // 公司Id

	public SuperParam() {
	}

	public void setCompanyId(Integer companyId) {
		if (companyId == null || companyId < 1)
			throw new SaasException();

		this.companyId = companyId;
	}

}
