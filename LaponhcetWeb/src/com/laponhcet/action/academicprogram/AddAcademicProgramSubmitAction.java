package com.laponhcet.action.academicprogram;

public class AddAcademicProgramSubmitAction extends AcademicProgramAction {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}