package com.laponhcet.action.fee;

public class AddFeeStudentSpecificFeeSubmitAction extends StudentSpecificFeeAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setCurrentLink(sessionInfo.getPreviousLink());
	}
}
