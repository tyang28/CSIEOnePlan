package com.suke.czx.modules.user.user.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * career
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-30 22:12:39
 */
public class CareerEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//career_id
	private Integer careerId;
	//name
	private String name;
	//skill
	private String skillId;
	//detail
	private String detail;

	/**
	 * 设置：career_id
	 */
	public void setCareerId(Integer careerId) {
		this.careerId = careerId;
	}
	/**
	 * 获取：career_id
	 */
	public Integer getCareerId() {
		return careerId;
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
	 * 设置：skill
	 */
	public void setSkillId(String skillId) {
		this.skillId = skillId;
	}
	/**
	 * 获取：skill
	 */
	public String getSkillId() {
		return skillId;
	}
	/**
	 * 设置：detail
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**
	 * 获取：detail
	 */
	public String getDetail() {
		return detail;
	}
}
