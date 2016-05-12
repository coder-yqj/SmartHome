package com.bishe.home.dao.impl;

import org.springframework.stereotype.Repository;

import com.bishe.home.dao.DaoSupport;
import com.bishe.home.dao.SceneDao;
import com.bishe.home.entity.Scene;

@Repository("sceneDao")
public class SceneDaoImpl extends DaoSupport<Scene> implements SceneDao{

	@Override
	public void updateName(Scene scene) {
		Scene entity = this.find(scene.getId());
		entity.setSceneName(scene.getSceneName());
		getHibernateTemplate().update(entity);
	}

	@Override
	public void deleteById(Integer id) {
		this.delete(id);
	}



	

	
}
