package com.bishe.home.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

public class GetSessionUtil {
	public static HttpSession getSession(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(); 
		return session;
	}
	
}
