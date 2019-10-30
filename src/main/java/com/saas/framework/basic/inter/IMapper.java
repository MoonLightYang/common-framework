package com.saas.framework.basic.inter;

import java.util.List;

import com.saas.framework.params.Id;
import com.saas.framework.params.QueryPage;

public interface IMapper<T, P> {

	/**
	 * 新增
	 */
	Integer add(T t);

	/**
	 * 修改对象
	 */
	Integer update(T t);

	/**
	 * 根据Id查询
	 */
	T find(Id id);

	/**
	 * 分页查询
	 */
	List<P> findPage(QueryPage query);

	/**
	 * 分页查询统计总数
	 */
	Long findPageCount(QueryPage query);

	/**
	 * 根据Id删除
	 */
	Integer del(Id id);

	/**
	 * 查询记录数量
	 */
	Long findCount();

}
