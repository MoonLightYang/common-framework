package com.saas.framework.params;

import com.saas.framework.annotation.DocField;

import lombok.Getter;
import lombok.Setter;

public class IdVersion extends SuperParam {

	@Getter
	@Setter
	@DocField(name = "id", remark = "数据记录id,【修改】必传，新增忽略")
	public Integer id;

	@Getter
	@Setter
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
