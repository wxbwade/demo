package com.example.demo.service.support;

import java.util.List;

public interface ExampleCrudMapper<P, T, ID> extends CrudMapper<T, ID> {

	/**
	 * 根据example进行查询多个
	 *
	 * @param params
	 *            {@link P} 查询条件
	 * @return
	 */
	List<T> selectByExample(P params);

	/**
	 * 根据example进行查询多个
	 *
	 * @param params
	 *            {@link P} 查询条件
	 * @return
	 */
	List<T> selectByExampleWithBLOBs(P params);

	/**
	 * 根据example进行统计数量
	 * 
	 * @param params
	 *            {@link P} 查询条件
	 * @return count
	 */
	Integer countByExample(P params);

	/**
	 * 根据example进行删除
	 * 
	 * @param params
	 */
	void deleteByExample(P params);

}
