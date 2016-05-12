package com.bishe.home.service;

import com.bishe.home.entity.User;

public interface LoginService {
	
	public User login(String userName);
	
	public void register(User user);
	
}
