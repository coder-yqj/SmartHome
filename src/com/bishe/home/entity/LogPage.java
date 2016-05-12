package com.bishe.home.entity;

import java.util.Date;

public class LogPage {
	private Integer sceneId;
	private Integer eqId;
	private Date startDate;
	private Date endDate;
	private Integer type;
	public Integer getSceneId() {
		return sceneId;
	}
	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}
	public Integer getEqId() {
		return eqId;
	}
	public void setEqId(Integer eqId) {
		this.eqId = eqId;
	}
	 
	 
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public LogPage() {
		super();
	}
	@Override
	public String toString() {
		return "LogPage [sceneId=" + sceneId + ", eqId=" + eqId
				+ ", startDate=" + startDate + ", endDate=" + endDate
				+ ", type=" + type + "]";
	}
	 
	
}
