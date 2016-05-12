package com.bishe.home.dao;

import java.util.LinkedHashMap;

import com.bishe.home.entity.OperationLog;
import com.bishe.home.entity.QueryResult;

public interface OperationLogDao {
	
	
	/**
	 * 条件查询日志
	 * @param firstResult
	 * @param maxResult
	 * @param where
	 * @param params
	 * @param orderby
	 * @return
	 */
	public QueryResult<OperationLog> getScrollData(int firstResult, int maxResult, String where, Object[] params, LinkedHashMap<String, String> orderby);
	
	
	public void save(OperationLog operationLog);
}
