package com.laponhcet.action.student;

import com.laponhcet.dto.StudentDTO;

public class UploadProfilePictSubmitAction extends StudentAction {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setCurrentLink(sessionInfo.getPreviousLink());
	}
	
	protected void validateInput() {
		StudentDTO student = (StudentDTO) getSessionAttribute(StudentDTO.SESSION_STUDENT);
	}
}