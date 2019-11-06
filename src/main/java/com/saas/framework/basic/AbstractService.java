package com.saas.framework.basic;

import java.util.Collections;
import java.util.List;

import com.saas.framework.basic.inter.IMapper;
import com.saas.framework.params.Id;
import com.saas.framework.params.IdVersion;
import com.saas.framework.params.QueryPage;
import com.saas.framework.view.PageList;

public abstract class AbstractService<T, P> {

	public abstract IMapper<T, P> mapper();

	/**
	 * 新增
	 */
	public Integer add(T t) {
		return this.mapper().add(t);
	}

	/**
	 * 根据Id查询
	 */
	public T find(Id id) {
		return this.mapper().find(id);
	}

	/**
	 * 分页查询
	 */
	public PageList<P> findPage(QueryPage query) {
		List<P> rows = this.mapper().findPage(query);
		rows = (rows == null ? rows = Collections.emptyList() : rows);
		PageList<P> page = new PageList<P>(query.getPageNo(), query.getPageSize(), rows);

		Long total = this.mapper().findPageCount(query);
		page.setTotal(total);
		return page;
	}

	/**
	 * 根据Id删除
	 */
	public Integer del(IdVersion idVersion) {
		return this.mapper().del(idVersion);
	}

	/**
	 * 修改信息
	 */
	public Integer update(T t) {
		return this.mapper().update(t);
	}

}
