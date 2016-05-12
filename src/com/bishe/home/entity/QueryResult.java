package com.bishe.home.entity;

import java.util.List;
/**
 * 查询结果
 * @param <T>
 */
public class QueryResult<T> {
	/* 记录集 */
	private List<T> rows;
	/* 总记录数 */
	private long total;
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
	 
	
}
