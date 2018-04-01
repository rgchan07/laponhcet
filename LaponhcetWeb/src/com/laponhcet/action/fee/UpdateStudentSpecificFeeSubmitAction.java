package com.laponhcet.action.fee;

public class UpdateStudentSpecificFeeSubmitAction extends StudentSpecificFeeAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionLinkOnSubmit();
	}
}
