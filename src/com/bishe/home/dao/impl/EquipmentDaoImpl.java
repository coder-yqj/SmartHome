package com.bishe.home.dao.impl;

import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bishe.home.dao.DaoSupport;
import com.bishe.home.dao.EquipmentDao;
import com.bishe.home.entity.Equipment;
import com.bishe.home.entity.QueryResult;
@Repository("equipmentDao")
public class EquipmentDaoImpl extends DaoSupport<Equipment> implements EquipmentDao{

	@Override
	public Equipment findById(Integer id) {
		List<Equipment> equipments = getHibernateTemplate().find("FROM Equipment WHERE id ="+ id);
		Equipment equipment = new Equipment();
		if(equipments.size()!= 0){
			equipment = equipments.get(0);
		}else{
			equipment = null;
		}
		return equipment;
	}

	@Override
	public void deleteById(Integer id) {
		//this.delete(id);
		String hql = "delete from Equipment where id= "+ id;
		getHibernateTemplate().bulkUpdate(hql);
		
	}

	@Override
	public QueryResult<Equipment> getScrollData(String where, Object[] params) {
		LinkedHashMap<String, String> orderby = new LinkedHashMap<>();
		orderby.put("id", "asc");
		return this.getScrollData(-1, -1, where, params, orderby);
	}

	@Override
	public void updateAttribute(Equipment equipment) {
		Equipment eq = this.find(equipment.getId());
		eq.setName(equipment.getName());
		eq.setEqComment(equipment.getEqComment());
		eq.setIsRemind(equipment.getIsRemind());
		getHibernateTemplate().update(eq);
	}

	@Override
	public void turnOn(Integer equipmentId) {
		Equipment eq = this.find(equipmentId);
		eq.turnOn();
		getHibernateTemplate().update(eq);
	}

	@Override
	public void turnOff(Integer equipmentId) {
		Equipment eq = this.find(equipmentId);
		eq.turnOff();
		getHibernateTemplate().update(eq);
		
	}

	@Override
	public void changeTo(Double value, Integer equipmentId) {
		Equipment eq = this.find(equipmentId);
		eq.setValue(value);
		getHibernateTemplate().update(eq);
	}


	
	

}
