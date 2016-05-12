package com.bishe.home.dao;

import com.bishe.home.entity.User;

 

public interface UserDao {
	public void save(User user);
	
	public Integer saveUser(User user);
 
	public User findByUserName(String userName);

}
