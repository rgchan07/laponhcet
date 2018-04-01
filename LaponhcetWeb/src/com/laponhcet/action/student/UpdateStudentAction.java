package com.laponhcet.action.student;

import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.base.ActionBase;

public class UpdateStudentAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			StudentDTO student = (StudentDTO) getSelectedPaginationObjById(StudentDTO.SESSION_STUDENT_PAGINATION, getRequestInt("txtSelectedRecord"));
			setSessionAttribute(StudentDTO.SESSION_STUDENT + "_ORIG", student);
			setSessionBeforeTrans(StudentDTO.SESSION_STUDENT, student.getStudent());
		}
	}
}