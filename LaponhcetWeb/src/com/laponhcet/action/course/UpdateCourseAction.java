package com.laponhcet.action.course;

import com.laponhcet.dto.CourseDTO;
import com.mytechnopal.base.ActionBase;

public class UpdateCourseAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		//is previous link from the list
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			CourseDTO course = (CourseDTO) getSelectedPaginationObjById(CourseDTO.SESSION_COURSE_PAGINATION, getRequestInt("txtSelectedRecord"));

			setSessionAttribute(CourseDTO.SESSION_COURSE + "_ORIG", course);
			setSessionBeforeTrans(CourseDTO.SESSION_COURSE, course.getCourse());
		}
	}
}
