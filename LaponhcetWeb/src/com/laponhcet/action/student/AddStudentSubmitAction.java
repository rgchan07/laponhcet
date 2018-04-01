package com.laponhcet.action.student;

public class AddStudentSubmitAction extends StudentAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
