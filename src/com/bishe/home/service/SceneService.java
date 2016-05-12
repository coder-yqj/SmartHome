package com.bishe.home.service;

import com.bishe.home.entity.QueryResult;
import com.bishe.home.entity.Scene;

public interface SceneService {
	
	public QueryResult<Scene> findAll();
	
	public void save(Scene scene);
	
	public void update(Scene scene);
	
	public void delete(Integer id);
	
}
