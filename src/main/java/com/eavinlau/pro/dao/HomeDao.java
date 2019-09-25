package com.eavinlau.pro.dao;

import java.util.List;

import com.eavinlau.fw.dao.BaseDao;
import com.eavinlau.pro.entity.HomeData;

public interface HomeDao extends BaseDao<HomeData> {
	
	public List<HomeData> findSearchList(String searchStr);

	public List<String> getTypeList();

	public List<HomeData> getListByTitle(String title);
	
}
