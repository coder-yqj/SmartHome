package com.bishe.home.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bishe.home.dao.UserDao;
import com.bishe.home.entity.User;
import com.bishe.home.service.EquipmentService;
import com.bishe.home.service.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Resource
	private UserDao userDao;
	
	@Resource
	private EquipmentService equipmentService;
	
	@Override
	public User login(String userName) {
		return userDao.findByUserName(userName);
	}

	
	@Override
	@Transactional
	public void register(User user) {
		Integer userId =  userDao.saveUser(user);
		equipmentService.addDefaultEq(userId);
	}
	
	
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	
	
	
}
