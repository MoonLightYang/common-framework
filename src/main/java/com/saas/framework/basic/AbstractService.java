package com.saas.framework.basic;

import java.util.Collections;
import java.util.List;

import com.saas.framework.basic.inter.IMapper;
import com.saas.framework.params.Id;
import com.saas.framework.params.QueryPage;
import com.saas.framework.view.PageList;

public abstract class AbstractService<T> {

	public abstract IMapper<T> mapper();

	/**
	 * 根据Id查询
	 */
	public T find(Id id) {
		return this.mapper().find(id);
	}

	/**
	 * 分页查询
	 */
	public PageList<T> findPage(QueryPage query) {
		List<T> rows = this.mapper().findPage(query);
		rows = (rows == null ? rows = Collections.emptyList() : rows); 
		PageList<T> page = new PageList<T>();
		page.setRows(rows);
		page.setPageNo(query.getPageNo());
		page.setPageSize(query.getPageSize());
		return page;
	}

	/**
	 * 根据Id删除
	 */
	public Integer del(Id id) {
		return this.mapper().del(id);
	}

}
