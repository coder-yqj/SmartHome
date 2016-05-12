package com.bishe.home.dao.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bishe.home.dao.DaoSupport;
import com.bishe.home.dao.UserDao;
import com.bishe.home.entity.Role;
import com.bishe.home.entity.User;


@Repository("userDao")
public class UserDaoImpl extends DaoSupport<User> implements UserDao {

	@Override
	public User findByUserName(String userName) {
		List<User> users =  getHibernateTemplate().find("FROM User WHERE userName='"+userName+"'");
		User user = new User();
		if(users.size()!=0){
			user = users.get(0);
		}else{
			user = null;
		}
		return user;	
	}

	@Override
	public Integer saveUser(User user) {
		getHibernateTemplate().save(user);
		return user.getId();
	}

//	@Resource
//	private HibernateTemplate hibernateTemplate;

 



}
