package com.laponhcet.action.course;

public class AddCourseSubmitAction extends CourseAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
