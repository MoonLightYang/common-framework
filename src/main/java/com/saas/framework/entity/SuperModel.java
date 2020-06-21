package com.saas.framework.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.saas.framework.enumer.YesOrNo;

import lombok.Getter;
import lombok.Setter;

public class SuperModel {

	@Getter
	@Setter
	protected Integer id; // 主键
	@Getter
	@Setter
	@JsonIgnore
	protected Integer enterpriseId; // 公司Id
	@Getter
	@Setter
	@JsonIgnore
	protected String enterprise; // 公司
	@Getter
	@Setter
	@JsonIgnore
	protected String handler; // 操作人昵称
	@Getter
	@Setter
	@JsonIgnore
	protected Integer handlerNo; // 操作人账户号
	@Getter
	@Setter
	@JsonIgnore
	protected YesOrNo isDelete; // 是否删除
	@Getter
	@Setter
	protected Integer version; // 版本号

	public SuperModel() {
	}

}
