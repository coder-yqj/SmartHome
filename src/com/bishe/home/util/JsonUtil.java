package com.bishe.home.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bishe.home.entity.QueryResult;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class JsonUtil {

	public static String getJsonStr(String key, Object object) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put(key, object);
		return jsonObj.toString();
	}
	
	public static <T> String getJsonStr(int state,Object data){
		return getJsonStr(state, data, null);
	} 
	
	
	public static <T> String getJsonStr(int state,Object data,String[] exclude){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);    
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonDateValueProcessor());
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(exclude);
		Map<String,Object> map = new HashMap<>();
		map.put("code", state);
		map.put("message", data);
		JSONObject jsonObj = JSONObject.fromObject(map, jsonConfig);
		return jsonObj.toString();
	}
	
	public static <T> String getJsonStr(int state,Object data,Object data2,String[] exclude){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);    
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonDateValueProcessor());
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(exclude);
		Map<String,Object> map = new HashMap<>();
		map.put("code", state);
		map.put("message", data);
		map.put("message2", data2);
		JSONObject jsonObj = JSONObject.fromObject(map, jsonConfig);
		return jsonObj.toString();
	}
	
	public static <T> String getJsonStr(int state,QueryResult<T> data,String[] exclude){
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setIgnoreDefaultExcludes(false);    
		jsonConfig.registerJsonValueProcessor(java.sql.Timestamp.class, new JsonDateValueProcessor());
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.setExcludes(exclude);
		Map<String,Object> map = new HashMap<>();
		map.put("code", state);
		map.put("rows", data.getRows());
		map.put("message", data.getRows());
		map.put("total", data.getTotal());
		JSONObject jsonObj = JSONObject.fromObject(map, jsonConfig);
		return jsonObj.toString();
	}

}
