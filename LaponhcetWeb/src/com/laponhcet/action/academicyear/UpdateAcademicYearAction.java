package com.laponhcet.action.academicyear;

import com.laponhcet.dto.AcademicYearDTO;
import com.mytechnopal.base.ActionBase;

public class UpdateAcademicYearAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
			AcademicYearDTO academicYear = (AcademicYearDTO) getSelectedPaginationObjById(AcademicYearDTO.SESSION_ACADEMIC_YEAR_PAGINATION, getRequestInt("txtSelectedRecord"));
			setSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR + "_ORIG", academicYear);
			setSessionBeforeTrans(AcademicYearDTO.SESSION_ACADEMIC_YEAR, academicYear.getAcademicYear());
		}
	}
}