package com.saas.framework.view;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class PageList<T> {

	@Getter
	@Setter
	public Integer pageSize;

	@Getter
	@Setter
	public Integer pageNo;

	@Getter
	@Setter
	public List<T> rows;

	public PageList() {
	}

	public PageList(Integer pageSize, Integer pageNo, List<T> rows) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.rows = rows;
	}

}
