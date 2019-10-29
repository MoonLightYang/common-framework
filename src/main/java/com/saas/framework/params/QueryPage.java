package com.saas.framework.params;

import lombok.Getter;

public class QueryPage extends SuperParam {

	public Integer pageSize = 15; // 分页大小

	public Integer pageNo = 1; // 分页编号

	public Integer start; // 开始数
	
	public Integer getStart() {
		this.start = this.pageSize * (this.pageNo - 1);
		return start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize < 1 || pageSize > 100)
			this.pageSize = 15;
		else
			this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		if (pageSize < 1 || pageSize > 100)
			this.pageNo = 1;
		else
			this.pageNo = pageNo;
	}

}
