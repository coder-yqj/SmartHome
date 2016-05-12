package com.bishe.home.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;

import com.bishe.home.entity.QueryResult;
import com.bishe.home.entity.Scene;

public interface SceneDao {
	/**
	 * 根据条件查询Scene对象
	 * @param firstResult
	 * @param maxResult
	 * @param where
	 * @param params
	 * @param orderby
	 * @return
	 */
	public QueryResult<Scene> getScrollData(int firstResult, int maxResult, String where, Object[] params, LinkedHashMap<String, String> orderby);
	
	/**
	 * 保存场景对象
	 * @param scene  场景对象
	 */
	public void save(Scene scene);
	
	/**
	 * 更新场景的场景名称
	 * @param scene 场景对象
	 */
	public void updateName(Scene scene);
	
	
	/**
	 * 删除场景
	 * @param id  场景id
 	 */
	public void deleteById(Integer id);
	
	public Scene find(Serializable entityid); 
	
}
