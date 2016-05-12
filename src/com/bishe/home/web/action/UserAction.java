package com.bishe.home.web.action;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import com.bishe.home.entity.User;
import com.bishe.home.service.UserService;


@Controller
public class UserAction{
	
	private User user = new User();
	
	@Resource
	private UserService userService;

	
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String save(){
//		userService.save(user);
		System.out.println(user);
		return "success";
	}
	

	
}
