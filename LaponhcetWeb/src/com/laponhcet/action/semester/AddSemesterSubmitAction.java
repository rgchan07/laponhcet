package com.laponhcet.action.semester;

public class AddSemesterSubmitAction extends SemesterAction {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
