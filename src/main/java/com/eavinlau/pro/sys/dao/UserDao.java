package com.eavinlau.pro.sys.dao;

import java.util.List;

import com.eavinlau.fw.dao.BaseDao;
import com.eavinlau.pro.sys.entity.UserData;

public interface UserDao extends BaseDao<UserData> {
	
	public List<UserData> getUserByName(String name);
	
	public List<UserData> getUserByNamePD(String name,String pd);

}
