package com.suke.czx.modules.user.user.service;

import com.suke.czx.modules.user.user.entity.CourseEntity;

import java.util.List;
import java.util.Map;

/**
 * course
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-30 19:11:47
 */
public interface CourseService {
	
	CourseEntity queryObject(Integer courseId);
	
	List<CourseEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CourseEntity course);
	
	void update(CourseEntity course);
	
	void delete(Integer courseId);
	
	void deleteBatch(Integer[] courseIds);
}
