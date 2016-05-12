package com.bishe.home.web.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.bishe.home.entity.User;
import com.bishe.home.service.EquipmentService;
import com.bishe.home.service.LoginService;
import com.bishe.home.service.UserService;
import com.bishe.home.util.JsonUtil;
import com.bishe.home.util.ReturnUtil;

@Controller
public class LoginAction {
	
	private Long channelId;
	private String userId;
	
	private User user = new User();
	@Resource
	private LoginService loginService;
	
	
	static Logger logger = Logger.getLogger(LoginAction.class);
	
	public String loginWeb() throws Exception{
		   HttpServletRequest request = ServletActionContext.getRequest();
			User dUser = loginService.login(user.getUserName());
			if(dUser==null){
				request.setAttribute("message", "无此账号！");
			}else if(!dUser.getPassword().equals(user.getPassword())){
				request.setAttribute("message", "密码错误！");
			}else{
				HttpSession session = request.getSession();
				dUser.setPassword("");
				session.setAttribute("user", dUser);
				session.setAttribute("channelId", channelId);
				session.setAttribute("userId", userId);
				return "index";
			}
			return "login";
		}
	
	public String login() throws Exception{
	   HttpServletRequest request = ServletActionContext.getRequest();
		User dUser = loginService.login(user.getUserName());
		if(dUser==null){
			request.setAttribute("message", "无此账号！");
		}else if(!dUser.getPassword().equals(user.getPassword())){
			request.setAttribute("message", "密码错误！");
		}else{
			HttpSession session = request.getSession();
			dUser.setPassword("");
			session.setAttribute("user", dUser);
			ReturnUtil.returnJsonStr(JsonUtil.getJsonStr("message", "登录成功"));
			return null;
			//return "index";
		}
		ReturnUtil.returnJsonStr(JsonUtil.getJsonStr("message", "账号密码错误"));
		return null;
		//return "login";
	}
	
	
	//用户注册
	public void register() throws Exception{
		logger.info("Enter register() Method... ");
		User rUser = loginService.login(user.getUserName());
		JSONObject jsonObj = new JSONObject();// new一个JSON
		if(rUser==null){
			loginService.register(user);
			jsonObj.accumulate("code", 0);
			jsonObj.accumulate("message", "注册成功");
			logger.info("注册成功 ");
		}else{
			logger.info("注册失败，用户名被占用 ");
			jsonObj.accumulate("code", 1);
			jsonObj.accumulate("message", "注册失败，用户名被占用");
		}
		ReturnUtil.returnJsonStr(jsonObj.toString());
		
	}
	
	public String registerWeb() throws Exception{
		 HttpServletRequest request = ServletActionContext.getRequest();
		logger.info("Enter register() Method... ");
		User rUser = loginService.login(user.getUserName());
		if(rUser==null){
			loginService.register(user);
			logger.info("注册成功 ");
			return "login";
		}else{
			logger.info("注册失败，用户名被占用 ");
			request.setAttribute("message", "用户名被占用");
			return "register";
		}
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginService getLoginService() {
		return loginService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	 
	
	
	
	
}
