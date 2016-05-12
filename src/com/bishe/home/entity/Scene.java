package com.bishe.home.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Scene implements Serializable {

	private static final long serialVersionUID = -5695212400932180486L;
	private Integer id;// 场景id
	private String sceneName;// 场景名称
	private String sceneImg;// 场景图标
	private Integer userId;// 用户id
//	private Set<Equipment> equipments = new HashSet();// 设备列表

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getSceneImg() {
		return sceneImg;
	}

	public void setSceneImg(String sceneImg) {
		this.sceneImg = sceneImg;
	}

//	public Set<Equipment> getEquipments() {
//		return equipments;
//	}
//
//	public void setEquipments(Set<Equipment> equipments) {
//		this.equipments = equipments;
//	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Scene other = (Scene) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Scene() {
	}

	public Scene(Integer id, String sceneName, String sceneImg, Integer userId) {
		super();
		this.id = id;
		this.sceneName = sceneName;
		this.sceneImg = sceneImg;
		this.userId = userId;
	}

	 
	
	
}
