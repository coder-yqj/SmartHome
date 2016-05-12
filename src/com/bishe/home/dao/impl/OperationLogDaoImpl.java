package com.bishe.home.dao.impl;

import org.springframework.stereotype.Repository;

import com.bishe.home.dao.DaoSupport;
import com.bishe.home.dao.OperationLogDao;
import com.bishe.home.entity.OperationLog;
@Repository("operationLogDao")
public class OperationLogDaoImpl extends DaoSupport<OperationLog> implements OperationLogDao{

}
