package com.suke.czx.modules.user.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.suke.czx.modules.user.user.dao.SkillDao;
import com.suke.czx.modules.user.user.entity.SkillEntity;
import com.suke.czx.modules.user.user.service.SkillService;



@Service("skillService")
public class SkillServiceImpl implements SkillService {
	@Autowired
	private SkillDao skillDao;
	
	@Override
	public SkillEntity queryObject(Integer skillId){
		return skillDao.queryObject(skillId);
	}
	
	@Override
	public List<SkillEntity> queryList(Map<String, Object> map){
		return skillDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return skillDao.queryTotal(map);
	}
	
	@Override
	public void save(SkillEntity skill){
		skillDao.save(skill);
	}
	
	@Override
	public void update(SkillEntity skill){
		skillDao.update(skill);
	}
	
	@Override
	public void delete(Integer skillId){
		skillDao.delete(skillId);
	}
	
	@Override
	public void deleteBatch(Integer[] skillIds){
		skillDao.deleteBatch(skillIds);
	}
	
}
