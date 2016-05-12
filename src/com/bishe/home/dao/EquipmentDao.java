package com.bishe.home.dao;

import java.util.LinkedHashMap;

import com.bishe.home.entity.Equipment;
import com.bishe.home.entity.QueryResult;

public interface EquipmentDao {
	
	/**
	 * 条件查找Equipment集合
	 * @param 开始索引,如果输入值为-1,即获取全部数据
	 * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
	 * @return 返回Equipment集合
	 */
	public QueryResult<Equipment> getScrollData(int firstResult, int maxResult, String where, Object[] params);
	
	/**
	 * 插入一个Equipment 对象
	 * @param equipment  equipment 对象
	 */
	public void save(Equipment equipment);
	
	/**
	 * 根据设备ID查找对应的Equipment对象
	 * @param id  设备ID
	 * @return Equipment对象
	 */
	public Equipment findById(Integer id);
	
	/**
	 * 根号传入的对象，修改相应的对象
	 * @param equipment
	 */
	public void update(Equipment equipment );
	
	/**
	 * 根据设备ID删除对应的设备
	 * @param id 设备ID
	 */
	public void deleteById(Integer id);
	
	/**
	 * 获取当用户所有设备
	 * @return 返回所有设备
	 */
	public QueryResult<Equipment> getScrollData(String where, Object[] params);
	
	
	/**
	 * 分页获取记录
	 * @param firstResult 开始索引,如果输入值为-1,即获取全部数据
	 * @param maxResult 每页获取的记录数,如果输入值为-1,即获取全部数据
	 * @param where 条件语句,不带where关键字,条件语句只能使用位置参数,位置参数的索引值以1开始,如:o.username=?1 and o.password=?2
	 * @param params 条件语句出现的位置参数值
	 * @param orderby 排序,Key为排序属性,Value为asc/desc,如:
	 *  LinkedHashMap<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("email", "asc");
		orderby.put("password", "desc");
	 * @return
	 */
	public QueryResult<Equipment> getScrollData(int firstResult, int maxResult, String where, Object[] params, LinkedHashMap<String, String> orderby);
	
	
	/**
	 * 更新设备的属性
	 * @param equipment
	 */
	public void updateAttribute(Equipment equipment );
	
	/**
	 * 打开设备的开关
	 * @param equipmentId
	 */
	public void turnOn(Integer equipmentId);
	
	/**
	 * 关闭设备的开关
	 * @param equipmentId
	 */
	public void turnOff(Integer equipmentId);
	
	
	public void changeTo(Double value ,Integer equipmentId);
	
}
