package com.bishe.home.domain;

import java.io.Serializable;

public class Message implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6912904765103630408L;
	
	private String title ;
	private String description;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Message(String title, String description) {
		super();
		this.title = title;
		this.description = description;
	}
	public Message() {
	}
	
	
}
