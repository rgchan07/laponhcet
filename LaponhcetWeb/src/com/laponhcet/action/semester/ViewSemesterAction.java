package com.laponhcet.action.semester;

import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.base.ActionBase;

public class ViewSemesterAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionAttribute(SemesterDTO.SESSION_SEMESTER, getSelectedPaginationObjById(SemesterDTO.SESSION_SEMESTER_PAGINATION, getRequestInt("txtSelectedRecord")));
	}
}