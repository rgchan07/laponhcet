package com.laponhcet.action.academicyear;

import com.laponhcet.dto.AcademicYearDTO;
import com.mytechnopal.base.ActionBase;

public class ViewAcademicYearAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
			setSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR, getSelectedPaginationObjById(AcademicYearDTO.SESSION_ACADEMIC_YEAR_PAGINATION, getRequestInt("txtSelectedRecord")));	
		}
	}
}