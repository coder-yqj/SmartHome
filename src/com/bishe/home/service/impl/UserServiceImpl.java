package com.bishe.home.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bishe.home.dao.UserDao;
import com.bishe.home.entity.Privilege;
import com.bishe.home.entity.Role;
import com.bishe.home.entity.User;
import com.bishe.home.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void save(User user) {
		 
//		Role role = new Role();
//		role.setRoleName("管理员");
//		Set<Role> roles = new HashSet<>();
//		roles.add(role);
//		
//		Privilege privilege1 = new Privilege();
//		privilege1.setPrivilegeName("会员管理");
//		
//		Privilege privilege2 = new Privilege();
//		privilege2.setPrivilegeName("消息管理");
//		
//		Set<Privilege> privileges = new HashSet<>();
//		privileges.add(privilege1);
//		privileges.add(privilege2);
//		
//		role.setPrivileges(privileges);
//		user.setRoles(roles);
//		
		userDao.save(user);
		System.out.println("UserServiceImpl.save");
	}
 
	@Override
	@Transactional(readOnly=true,propagation=Propagation.NOT_SUPPORTED )
	public User findById(Integer id) {
		return null;
	}

	@Override
	public User findUserByName(String userName) {
		return userDao.findByUserName(userName);
	}
	
	
}
