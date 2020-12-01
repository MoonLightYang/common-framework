package com.saas.framework.basic.inter;

import java.util.List;

import com.saas.framework.params.Id;
import com.saas.framework.params.IdVersion;
import com.saas.framework.params.IdsQuery;
import com.saas.framework.params.QueryPage;
import com.saas.framework.params.SuperParams;
import com.saas.framework.view.Options;

public interface IMapper<T, P> {

	/**
	 * 新增
	 */
	Integer insert(T t);

	/**
	 * 修改对象
	 */
	Integer modify(T t);

	/**
	 * 根据Id查询
	 */
	T find(Id id);

	/**
	 * 分页查询
	 */
	List<P> page(QueryPage query);

	/**
	 * 分页查询统计总数
	 */
	Long pageCount(QueryPage query);

	/**
	 * 根据Id删除
	 */
	Integer delete(IdVersion idVersion);

	/**
	 * 查询记录数量
	 */
	Long listCount(SuperParams params);

	/**
	 * 查询列表
	 * 
	 * @param listParams
	 * @return
	 */
	List<T> list(SuperParams params);

	/**
	 * 下拉选项
	 * 
	 * @param query
	 * @return
	 */
	List<Options> options(SuperParams params);

	/**
	 * 根据id集合查询
	 * 
	 * @param query
	 * @return
	 */
	List<T> findIn(IdsQuery ids);

}
