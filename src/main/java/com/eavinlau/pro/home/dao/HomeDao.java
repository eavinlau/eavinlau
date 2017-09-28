package com.eavinlau.pro.home.dao;

import java.util.List;

import com.eavinlau.fw.dao.BaseDao;
import com.eavinlau.pro.home.entity.HomeData;

public interface HomeDao extends BaseDao<HomeData> {
	
	public List<HomeData> findSearchList(String searchStr);

	public List<String> getTypeList();

	public List<HomeData> getListByTitle(String title);
	
}
