package com.laponhcet.action.academicyear;
import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicYearDTO;
import com.mytechnopal.base.ActionBase;

public class AddAcademicYearAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0104", "US0099", "US0105" }, new String[] { "US0100", "US0106", "US0108" }, new String[] { "US0101", "US0107", "US0109" }, "US0102", "US0103");
		if (!sessionInfo.isPreviousLinkAddSubmit()) {
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionBeforeTrans(AcademicYearDTO.SESSION_ACADEMIC_YEAR, new AcademicYearDTO());
		}
	}
}