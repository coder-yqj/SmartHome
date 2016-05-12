package com.bishe.home.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.bishe.home.entity.QueryResult;

/**
 * 实体操作通用接口
 *
 * @param <T> 实体类型
 */
public interface DAO<T> {
	
	/**
	 * 分页获取所有记录
	 * @return
	 */
	public QueryResult<T> getScrollData();
	/**
	 * 分页获取记录
	 * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
	 * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstResult, int maxResult);
	/**
	 * 分页获取记录
	 * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
	 * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
	 * @param orderby 排序,Key为排序属性,Value为asc/desc,如:
	 *  LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("email", "asc");
		orderby.put("password", "desc");
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstResult, int maxResult, LinkedHashMap<String, String> orderby);
	/**
	 * 分页获取记录
	 * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
	 * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
	 * @param where 条件语句,不带where关键字,条件语句只能使用位置参数,位置参数的索引值以1开始,如:o.username=?1 and o.password=?2
	 * @param params 条件语句出现的位置参数值
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstResult, int maxResult, String where, Object[] params);
	/**
	 * 分页获取记录
	 * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
	 * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
	 * @param where 条件语句,不带where关键字,条件语句只能使用位置参数,位置参数的索引值以1开始,如:o.username=?1 and o.password=?2
	 * @param params 条件语句出现的位置参数值
	 * @param orderby 排序,Key为排序属性,Value为asc/desc,如:
	 *  LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("email", "asc");
		orderby.put("password", "desc");
	 * @return
	 */
	public QueryResult<T> getScrollData(int firstResult, int maxResult, String where, Object[] params, LinkedHashMap<String, String> orderby);
	
	/**
	 * 保存实体
	 * @param entity 实体对象
	 */
	public void save(T entity);
	/**
	 * 更新实体
	 * @param entity 实体对象
	 */
	public void update(T entity);
	/**
	 * 删除实体
	 * @param entityid 实体标识
	 */
	public void delete(Serializable entityid);//JPA 规定实体的id属性必须实现序列化接口
	/**
	 * 获胜实体
	 * @param entityid 实体标识
	 * @return 实体对象
	 */
	public T find(Serializable entityid);
	/**
	 * 获取实体的总记录数
	 * @return 总记录数
	 */
	public long getCount();
}
