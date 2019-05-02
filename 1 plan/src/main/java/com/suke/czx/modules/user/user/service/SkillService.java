package com.suke.czx.modules.user.user.service;

import com.suke.czx.modules.user.user.entity.SkillEntity;

import java.util.List;
import java.util.Map;

/**
 * 技能
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-29 09:51:25
 */
public interface SkillService {
	
	SkillEntity queryObject(Integer skillId);
	
	List<SkillEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SkillEntity skill);
	
	void update(SkillEntity skill);
	
	void delete(Integer skillId);
	
	void deleteBatch(Integer[] skillIds);
}
