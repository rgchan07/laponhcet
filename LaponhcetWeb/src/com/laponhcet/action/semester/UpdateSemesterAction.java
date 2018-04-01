package com.laponhcet.action.semester;

import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.base.ActionBase;

public class UpdateSemesterAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			SemesterDTO semester = (SemesterDTO) getSelectedPaginationObjById(SemesterDTO.SESSION_SEMESTER_PAGINATION, getRequestInt("txtSelectedRecord"));
			setSessionAttribute(SemesterDTO.SESSION_SEMESTER + "_ORIG", semester);
			setSessionBeforeTrans(SemesterDTO.SESSION_SEMESTER, semester.getSemester());
		}
	}
}
