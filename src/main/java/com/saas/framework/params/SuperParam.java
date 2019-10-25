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
	public Integer enterpriseId; // 公司Id

	@Getter
	@Setter
	public Integer version; // version

	public SuperParam() {
	}

	public void setCompanyId(Integer enterpriseId) {
		if (enterpriseId == null || enterpriseId < 1)
			throw new SaasException();

		this.enterpriseId = enterpriseId;
	}

}
