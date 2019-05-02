package com.suke.czx.modules.user.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.suke.czx.modules.user.user.dao.InterestDao;
import com.suke.czx.modules.user.user.entity.InterestEntity;
import com.suke.czx.modules.user.user.service.InterestService;



@Service("interestService")
public class InterestServiceImpl implements InterestService {
	@Autowired
	private InterestDao interestDao;
	
	@Override
	public InterestEntity queryObject(Integer interId){
		return interestDao.queryObject(interId);
	}
	
	@Override
	public List<InterestEntity> queryList(Map<String, Object> map){
		return interestDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return interestDao.queryTotal(map);
	}
	
	@Override
	public void save(InterestEntity interest){
		interestDao.save(interest);
	}
	
	@Override
	public void update(InterestEntity interest){
		interestDao.update(interest);
	}
	
	@Override
	public void delete(Integer interId){
		interestDao.delete(interId);
	}
	
	@Override
	public void deleteBatch(Integer[] interIds){
		interestDao.deleteBatch(interIds);
	}
	
}
