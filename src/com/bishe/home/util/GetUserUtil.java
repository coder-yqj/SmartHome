package com.bishe.home.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.bishe.home.entity.User;

public class GetUserUtil {

	public static User getUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		return (User) session.getAttribute("user");
	}
	
}
