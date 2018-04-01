package com.laponhcet.action.student;

public class UpdateStudentSubmitAction extends StudentAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
	
}
