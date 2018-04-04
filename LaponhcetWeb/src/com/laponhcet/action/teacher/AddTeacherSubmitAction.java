package com.laponhcet.action.teacher;

public class AddTeacherSubmitAction extends TeacherAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
