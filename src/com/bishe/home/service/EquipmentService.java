package com.bishe.home.service;

import com.bishe.home.entity.Equipment;
import com.bishe.home.entity.OperationLog;
import com.bishe.home.entity.QueryResult;

public interface EquipmentService {
	
	public QueryResult<Equipment> findByPage(int page ,int row,Integer sceneId);
	
	public void save(Equipment equipment);
	
	public Equipment findById(Integer id);
	
	public void update(Equipment equipment);
	
	public void deleteById(Integer id);

	public QueryResult<Equipment> findAll();
	
	public QueryResult<Equipment> findBySceneId(Integer sceneId);
	/**
	 * 打开设备的开关
	 * @param equipmentId
	 */
	public OperationLog changeState(Equipment cEquipment);

//	public void turnOff(Integer equipmentId);
	/**
	 * 改变设备的当前值
	 * @param value
	 */
	public void chengeTo(Double value ,Integer equipmentId);
	
	/**
	 * 用户注册后添加默认的设备
	 */
	public void addDefaultEq(Integer userId);
	
	public QueryResult<Equipment> findTemperature();
	
	
}
