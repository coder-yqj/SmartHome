package com.bishe.home.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bishe.home.dao.OperationLogDao;
import com.bishe.home.entity.LogPage;
import com.bishe.home.entity.OperationLog;
import com.bishe.home.entity.QueryResult;
import com.bishe.home.service.OperationLogService;
import com.bishe.home.util.GetUserUtil;
@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService{
	
	@Resource(name="operationLogDao")
	private OperationLogDao operationLogDao;
	


	@Override
	public QueryResult<OperationLog> findByCondition(int page, int row,
			LogPage logPage) {
		int firstResult = (page-1)* row;
		int maxResult = page*row;
		Integer sceneId = logPage.getSceneId();
		Integer eqId = logPage.getEqId();
		Date startDate = logPage.getStartDate();
		Date endDate = logPage.getEndDate();
		Integer type = logPage.getType();
//		Object[] params = new Object[]{GetUserUtil.getUser().getId()};
		List<Object> list = new ArrayList<>();
		StringBuilder where = new StringBuilder("userId=?");
		list.add(GetUserUtil.getUser().getId());
		if(sceneId != null && sceneId !=0){
			where.append("and sceneId=?");
			list.add(sceneId);
		}
		
		if(eqId!=null&&eqId!=0){
			where.append("and equipmentId=?");
			list.add(eqId);
		}
		if(type!=null){
			where.append("and type=?");
			list.add(type);
		}
		if(startDate!= null&&!"".equals(startDate)){
			where.append(" and opTime>=?");
			list.add(startDate);
			System.out.println("startDate:"+startDate);
		}
		if(endDate != null&&!"".equals(endDate)){
			where.append(" and opTime<=?");
			list.add(endDate);
			System.out.println("endDate:"+endDate);
		}
		Object[] params = list.toArray();
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("opTime", "desc");
		
		
		QueryResult<OperationLog> result =  operationLogDao.getScrollData(firstResult, maxResult, where.toString(), params, orderby);
		for(OperationLog log :result.getRows()){
			log.setEquipmentName(log.getEquipment().getName());
			log.setSceneName(log.getScene().getSceneName());
		}
		return result;
	}



	@Override
	public void add(OperationLog operationLog) {
		operationLogDao.save(operationLog);
	}
	
	

}
