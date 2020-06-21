package com.saas.framework.params;

import javax.validation.constraints.NotNull;

import com.saas.framework.annotation.DocField;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Id extends SuperParams {

	@Getter
	@Setter
	@NotNull(message = "缺少必要查询条件")
	@DocField(name = "id", remark = "数据记录id")
	public Integer id;

	public Id(Integer id, Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
		this.id = id;
	}
}
