package com.suke.czx.modules.user.user.service;

import com.suke.czx.modules.user.user.entity.InterestEntity;

import java.util.List;
import java.util.Map;

/**
 * interst
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-29 15:56:17
 */
public interface InterestService {
	
	InterestEntity queryObject(Integer interId);
	
	List<InterestEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InterestEntity interest);
	
	void update(InterestEntity interest);
	
	void delete(Integer interId);
	
	void deleteBatch(Integer[] interIds);
}
