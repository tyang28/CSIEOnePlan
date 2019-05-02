package com.suke.czx.modules.user.user.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * course
 * 
 * @author czx
 * @email object_czx@163.com
 * @date 2019-04-30 19:11:47
 */
public class CourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//course_id
	private Integer courseId;
	//name
	private String name;
	//skill
	private String skillId;

	/**
	 * 设置：course_id
	 */
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	/**
	 * 获取：course_id
	 */
	public Integer getCourseId() {
		return courseId;
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
}
