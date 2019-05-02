package com.suke.czx.modules.user.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.suke.czx.modules.user.user.dao.CareerDao;
import com.suke.czx.modules.user.user.entity.CareerEntity;
import com.suke.czx.modules.user.user.service.CareerService;



@Service("careerService")
public class CareerServiceImpl implements CareerService {
	@Autowired
	private CareerDao careerDao;
	
	@Override
	public CareerEntity queryObject(Integer careerId){
		return careerDao.queryObject(careerId);
	}
	
	@Override
	public List<CareerEntity> queryList(Map<String, Object> map){
		return careerDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return careerDao.queryTotal(map);
	}
	
	@Override
	public void save(CareerEntity career){
		careerDao.save(career);
	}
	
	@Override
	public void update(CareerEntity career){
		careerDao.update(career);
	}
	
	@Override
	public void delete(Integer careerId){
		careerDao.delete(careerId);
	}
	
	@Override
	public void deleteBatch(Integer[] careerIds){
		careerDao.deleteBatch(careerIds);
	}
	
}
