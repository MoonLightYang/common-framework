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
	public Integer insert(T t) {
		return this.mapper().insert(t);
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
	public PageList<P> page(QueryPage query) {
		List<P> rows = this.mapper().page(query);
		rows = (rows == null ? rows = Collections.emptyList() : rows);
		PageList<P> page = new PageList<P>(query.getPageNo(), query.getPageSize(), rows);

		Long total = this.mapper().pageCount(query);
		page.setTotal(total);
		return page;
	}

	/**
	 * 根据Id删除
	 */
	public Integer delete(IdVersion idVersion) {
		return this.mapper().delete(idVersion);
	}

	/**
	 * 修改信息
	 */
	public Integer modify(T t) {
		return this.mapper().modify(t);
	}

	/**
	 * 获取所有
	 */
	public List<T> list() {
		return this.mapper().list();
	}
//
//	public List<Options> listOptions(SuperParams optionsParams) {
//		return this.mapper().listOptions(optionsParams);
//	}

}
