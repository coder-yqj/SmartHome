package com.bishe.home.web.action;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.bishe.home.entity.LogPage;
import com.bishe.home.entity.OperationLog;
import com.bishe.home.entity.QueryResult;
import com.bishe.home.service.OperationLogService;
import com.bishe.home.util.JsonDateValueProcessor;
import com.bishe.home.util.JsonUtil;
import com.bishe.home.util.ReturnUtil;

@Controller
public class OperationLogAction {
	
	private LogPage logPage = new LogPage();
	private Integer page;
	private Integer rows;
	private JSONObject jsonObj;

	@Resource(name = "operationLogService")
	private OperationLogService operationLogService;

	static Logger logger = Logger.getLogger(OperationLogAction.class);

	// 根据设备ID，开始时间，结束时间查找相应的日志
	public void findByCondition() throws Exception {
		logger.info("查询条件："+ logPage+"--rwos:"+rows+"--page:"+page);
		try {
		QueryResult<OperationLog> queryResult = operationLogService
				.findByCondition(page, rows, logPage);
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(1, queryResult, new String[]{"equipment","scene"}));
		} catch (Exception e) {
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr(0, "查询日志出错"));
			e.printStackTrace();
		}
	}
	
	


	public LogPage getLogPage() {
		return logPage;
	}


	public void setLogPage(LogPage logPage) {
		this.logPage = logPage;
	}


	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public JSONObject getJsonObj() {
		return jsonObj;
	}

	public void setJsonObj(JSONObject jsonObj) {
		this.jsonObj = jsonObj;
	}

}
