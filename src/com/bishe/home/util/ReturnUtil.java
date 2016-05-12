package com.bishe.home.util;

import org.apache.struts2.ServletActionContext;

public class ReturnUtil {
	/**
	 * 
	 * @param jsonStr
	 * @throws Exception
	 */
	public static void returnJsonStr(String jsonStr) throws Exception {
		javax.servlet.http.HttpServletRequest request = ServletActionContext.getRequest();
		javax.servlet.http.HttpServletResponse response = ServletActionContext.getResponse();
		//request.setCharacterEncoding("UTF-8");
		response.setContentType("text/plain;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		byte[] jsonBytes = jsonStr.toString().getBytes("utf-8");
		response.setContentLength(jsonBytes.length);
		response.getOutputStream().write(jsonBytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
