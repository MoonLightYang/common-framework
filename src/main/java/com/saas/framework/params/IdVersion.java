package com.saas.framework.params;

import javax.validation.constraints.NotNull;

import com.saas.framework.annotation.DocField;

import lombok.Getter;
import lombok.Setter;

public class IdVersion extends SuperParams {

	@Getter
	@Setter
	@NotNull(message = "缺少必要查询条件")
	@DocField(name = "id", remark = "必传字段")
	public Integer id;

	@Getter
	@Setter
	@NotNull(message = "缺少必要查询条件")
	@DocField(name = "version", remark = "必须字段")
	public Integer version;

	public IdVersion() {

	}

	public IdVersion(Integer id) {
		this.id = id;
	}

	public IdVersion(Integer id, Integer version) {
		this.id = id;
		this.version = version;
	}

}
