package com.laponhcet.action.course;

public class UpdateCourseSubmitAction extends CourseAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
