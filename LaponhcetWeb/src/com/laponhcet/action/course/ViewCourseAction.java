package com.laponhcet.action.course;

import com.laponhcet.dto.CourseDTO;
import com.mytechnopal.base.ActionBase;

public class ViewCourseAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		setSessionAttribute(CourseDTO.SESSION_COURSE, getSelectedPaginationObjById(CourseDTO.SESSION_COURSE_PAGINATION, getRequestInt("txtSelectedRecord")));
	}
}