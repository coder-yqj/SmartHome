package com.bishe.home.service;

import com.bishe.home.entity.User;



public interface UserService {
	
	public void save(User user);
	
	public User findById(Integer id);
	
	public User findUserByName(String userName);
}
