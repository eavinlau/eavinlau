package com.eavinlau.pro.home.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eavinlau.pro.home.dao.HomeDao;
import com.eavinlau.pro.home.entity.HomeData;

@Service("homeService")
@Transactional
public class HomeService {
	@Autowired
	HomeDao homeDao;
	
	public List<HomeData> findAllList(){
		return homeDao.findAllList(); 
	}
	
	
	public HomeData get(String id){
		return homeDao.get(id); 
	}
	
	
	public List<HomeData> findSearchList(String searchStr) {
		return homeDao.findSearchList(searchStr);
	}
	
	
	public void update(HomeData h) {
		homeDao.update(h);
	}

	
	public List<HomeData> findList(HomeData h) {
		return homeDao.findList(h);
	}

	
	public List<String> getTypeList() {
		return homeDao.getTypeList();
	}
	
	
	public void insert(HomeData h) {
		homeDao.insert(h);
	}
	
	
	public void delete(String id) {
		homeDao.delete(id);
	}
	
	
	public List<HomeData> getListByTitle(String title) {
		return homeDao.getListByTitle(title);
	}

}
