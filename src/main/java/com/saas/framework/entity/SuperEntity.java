package com.saas.framework.entity;

import com.saas.framework.enumer.YesOrNo;

import lombok.Getter;
import lombok.Setter;

public class SuperEntity {

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
	public YesOrNo is_delete; // 是否可用数据
	@Getter
	@Setter
	public Integer version; // 版本号

	public SuperEntity() {
	}

}
