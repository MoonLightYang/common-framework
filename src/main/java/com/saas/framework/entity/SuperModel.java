package com.saas.framework.entity;

import com.saas.framework.enumer.YesOrNo;

import lombok.Getter;
import lombok.Setter;

public class SuperModel {

	@Getter
	@Setter
	protected Integer id; // 主键
	@Getter
	@Setter
	protected Integer enterpriseId; // 公司Id
	@Getter
	@Setter
	protected String enterprise; // 公司
	@Getter
	@Setter
	protected String handler; // 操作人昵称
	@Getter
	@Setter
	protected Integer handlerNo; // 操作人账户号
	@Getter
	@Setter
	protected YesOrNo isDelete; // 是否删除
	@Getter
	@Setter
	protected Integer version; // 版本号

	public SuperModel() {
	}

}
