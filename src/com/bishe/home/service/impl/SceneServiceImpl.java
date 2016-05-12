package com.bishe.home.service.impl;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bishe.home.dao.SceneDao;
import com.bishe.home.entity.QueryResult;
import com.bishe.home.entity.Scene;
import com.bishe.home.service.SceneService;
import com.bishe.home.util.GetUserUtil;

@Service("sceneService")
public class SceneServiceImpl implements SceneService{
	
	@Resource(name="sceneDao")
	private SceneDao sceneDao;
	
	@Override
	public QueryResult<Scene> findAll() {
		String where = "userId=?";
		Object[] params = new Object[]{GetUserUtil.getUser().getId()};
		LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("id", "asc");
		return sceneDao.getScrollData(-1, -1, where, params, orderby);
	}

	@Override
	public void save(Scene scene) {
		scene.setUserId(GetUserUtil.getUser().getId());
		sceneDao.save(scene);
	}

	@Override
	public void update(Scene scene) {
		sceneDao.updateName(scene);
	}

	@Override
	public void delete(Integer id) {
		sceneDao.deleteById(id);
	}

}
