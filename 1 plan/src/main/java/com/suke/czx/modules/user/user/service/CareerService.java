package com.suke.czx.modules.user.user.service;

import com.suke.czx.modules.user.user.entity.CareerEntity;

import java.util.List;
import java.util.Map;

/**
 * career
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-30 22:12:39
 */
public interface CareerService {
	
	CareerEntity queryObject(Integer careerId);
	
	List<CareerEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CareerEntity career);
	
	void update(CareerEntity career);
	
	void delete(Integer careerId);
	
	void deleteBatch(Integer[] careerIds);
}
