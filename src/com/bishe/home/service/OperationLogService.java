package com.bishe.home.service;

import com.bishe.home.entity.LogPage;
import com.bishe.home.entity.OperationLog;
import com.bishe.home.entity.QueryResult;

public interface OperationLogService {
	
	public QueryResult<OperationLog> findByCondition(int page ,int row ,LogPage logPage);
	
	
	public void add(OperationLog operationLog);
	
}
