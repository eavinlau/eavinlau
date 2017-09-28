package com.eavinlau.pro.sys.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eavinlau.pro.sys.dao.UserDao;
import com.eavinlau.pro.sys.entity.UserData;

@Service("userService")
@Transactional
public class UserService {
	@Autowired
	UserDao userDao;
	
	public List<UserData> getUserByName(String name){
		return userDao.getUserByName(name); 
	}

	public List<UserData> getUserByNamePD(String name,String pd) {
		return userDao.getUserByNamePD(name,pd);
	}

	public void register(UserData u) {
		userDao.insert(u);
	}
	
	public void delete(String id) {
		userDao.delete(id);
	}

	public List<UserData> findAllList(){
		return userDao.findAllList(); 
	}
	
}
