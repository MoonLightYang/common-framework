package com.saas.framework.params;

import lombok.Getter;
import lombok.Setter;

public class Id extends SuperParam {

	@Getter
	@Setter
	public Integer id; // 物理主键

	@Getter
	@Setter
	public Integer companyId;// 企业Id

	public Id() {

	}

}
