package com.suke.czx.modules.user.user.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 技能
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-29 09:51:25
 */
public class SkillEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer skillId;
	//技能名称
	private String name;

	/**
	 * 设置：
	 */
	public void setSkillId(Integer skillId) {
		this.skillId = skillId;
	}
	/**
	 * 获取：
	 */
	public Integer getSkillId() {
		return skillId;
	}
	/**
	 * 设置：技能名称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：技能名称
	 */
	public String getName() {
		return name;
	}
}
