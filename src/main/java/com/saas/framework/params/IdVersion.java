package com.saas.framework.params;

import javax.validation.constraints.NotNull;

import com.saas.framework.annotation.DocField;

import lombok.Getter;
import lombok.Setter;

public class IdVersion extends SuperParam {

	@Getter
	@Setter
	@NotNull
	@DocField(name = "id", remark = "数据记录id")
	public Integer id;

	@Getter
	@Setter
	@NotNull
	@DocField(name = "version", remark = "【修改】或【删除】必须字段")
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
