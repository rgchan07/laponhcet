package com.laponhcet.action.course;

import com.laponhcet.dto.CourseDTO;
import com.mytechnopal.base.ActionBase;

public class DeleteCourseSubmitAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionBeforeTrans(CourseDTO.SESSION_COURSE, getSelectedPaginationObjById(CourseDTO.SESSION_COURSE_PAGINATION, getRequestInt("txtSelectedRecord")));
	}
}
