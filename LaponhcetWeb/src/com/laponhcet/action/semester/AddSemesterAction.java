package com.laponhcet.action.semester;

import java.util.List;

import com.laponhcet.dto.AcademicYearDTO;
import com.laponhcet.dto.SemesterDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;

public class AddSemesterAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0116", "US0111", "US0117"}, new String[] {"US0112", "US0118", "US0120"}, new String[] {"US0113", "US0119", "US0121"}, "US0114", "US0115");
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			SemesterDTO semester = new SemesterDTO();
			List<DTOBase> academicYearList = (List<DTOBase>) getSessionAttribute(AcademicYearDTO.SESSION_ACADEMIC_YEAR_LIST);
			semester.setAcademicYear((AcademicYearDTO) academicYearList.get(0));
			setSessionBeforeTrans(SemesterDTO.SESSION_SEMESTER, semester);
		}
	}
}