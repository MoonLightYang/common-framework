package com.saas.framework.entity;

import com.saas.framework.enumer.YesOrNo;

import lombok.Getter;
import lombok.Setter;

public class SuperModel {

	@Getter
	@Setter
	public Integer id; // 主键
	@Getter
	@Setter
	public Integer enterpriseId; // 公司Id
	@Getter
	@Setter
	public String handler; // 操作人昵称
	@Getter
	@Setter
	public Integer handlerNo; // 操作人账户号
	@Getter
	@Setter
	public YesOrNo isDelete; // 是否删除
	@Getter
	@Setter
	public Integer version; // 版本号

	public SuperModel() {
	}

}
