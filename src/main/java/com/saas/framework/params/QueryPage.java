package com.saas.framework.params;

public class QueryPage extends SuperParams {

	public Integer pageSize = 15;

	public Integer pageNo = 1;

	public Integer start = 0;

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
