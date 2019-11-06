package com.saas.framework.params;

import javax.validation.constraints.NotNull;

import com.saas.framework.annotation.DocField;

import lombok.Getter;
import lombok.Setter;

public class Id extends SuperParam {

	@Getter
	@Setter
	@NotNull
	@DocField(name = "id", remark = "数据记录id")
	public Integer id;

	public Id() {

	}

	public Id(Integer id) {
		this.id = id;
	}

}
