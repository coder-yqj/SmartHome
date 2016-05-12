package com.bishe.home.entity;

import java.io.Serializable;

public class Equipment implements Serializable{

	private static final long serialVersionUID = -6377533676968169700L;
	private Integer id; // id
	private String name; // 设备名称
	private Double value; // 值
	private Double highValue; // 阀值上限
	private Double lowValue; // 阀值下限
	private Integer state; // 开关状态 0:关 1：开
	private String image; // 图片
	private String eqComment;// 备注
	private Integer isRemind; // 是否提醒 0:不提醒 1:提醒
	private Integer userId; // 设备所对应的用户ID
	private Integer sceneId; //场景id
	private Scene scene;	//场景
	private Integer type; //0:开关设备  1：温度  2：水 3：电   4:煤气
	
	public void turnOn() {
		this.state = 1;
	}

	public void turnOff() {
		this.state = 0;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Double getHighValue() {
		return highValue;
	}

	public void setHighValue(Double highValue) {
		this.highValue = highValue;
	}

	public Double getLowValue() {
		return lowValue;
	}

	public void setLowValue(Double lowValue) {
		this.lowValue = lowValue;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getEqComment() {
		return eqComment;
	}

	public void setEqComment(String eqComment) {
		this.eqComment = eqComment;
	}

	public Integer getIsRemind() {
		return isRemind;
	}

	public void setIsRemind(Integer isRemind) {
		this.isRemind = isRemind;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSceneId() {
		return sceneId;
	}

	public void setSceneId(Integer sceneId) {
		this.sceneId = sceneId;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}
	

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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
		Equipment other = (Equipment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Equipment [id=" + id + ", name=" + name + ", value=" + value
				+ ", highValue=" + highValue + ", lowValue=" + lowValue
				+ ", state=" + state + ", image=" + image + ", eqComment="
				+ eqComment + ", isRemind=" + isRemind + ", userId=" + userId
				+ ", sceneId=" + sceneId + ", scene=" + scene + "]";
	}


	 

 

}
