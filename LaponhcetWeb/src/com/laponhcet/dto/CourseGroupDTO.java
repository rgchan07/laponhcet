package com.laponhcet.dto;

import com.mytechnopal.base.DTOBase;

public class CourseGroupDTO extends DTOBase {
	private static final long serialVersionUID = 1L;

	public static final String SESSION_COURSE_GROUP = "SESSION_COURSE_GROUP";
	public static final String SESSION_COURSE_GROUP_LIST = "SESSION_COURSE_GROUP_LIST";
	public static final String SESSION_COURSE_GROUP_PAGINATION = "SESSION_COURSE_GROUP_PAGINATION";
	
	public static final String[] PAGINATION_SEARCH_CRITERIA_LIST = new String[] {"Name", "Description"};
	
	private String name;
	private String description;
	
	public CourseGroupDTO() {
		super();
		this.name = "";
		this.description = "";
	}
	
	public CourseGroupDTO getCourseGroup() {
		CourseGroupDTO courseGroup = new CourseGroupDTO();
		courseGroup.setId(super.getId());
		courseGroup.setCode(super.getCode());
		courseGroup.setName(this.name);
		courseGroup.setDescription(this.description);
		return courseGroup;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
