package com.suke.czx.modules.user.user.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * interst
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-29 15:56:17
 */
public class InterestEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//inter_id
	private Integer interId;
	//name
	private String name;
	//
	private String skillId;

	/**
	 * 设置：inter_id
	 */
	public void setInterId(Integer interId) {
		this.interId = interId;
	}
	/**
	 * 获取：inter_id
	 */
	public Integer getInterId() {
		return interId;
	}
	/**
	 * 设置：name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：name
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	/**
	 * 获取：
	 */
	public String getSkillId() {
		return skillId;
	}
}
