package com.laponhcet.action.student;

import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.base.ActionBase;

public class ViewStudentAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		setSessionAttribute(StudentDTO.SESSION_STUDENT, getSelectedPaginationObjById(StudentDTO.SESSION_STUDENT_PAGINATION, getRequestInt("txtSelectedRecord")));
	}
}