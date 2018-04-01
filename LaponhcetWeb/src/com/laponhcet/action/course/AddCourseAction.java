package com.laponhcet.action.course;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.CourseGroupDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.CourseDTO;
import com.laponhcet.dto.CourseGroupDTO;
import com.mytechnopal.base.ActionBase;

public class AddCourseAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0128", "US0123", "US0129"}, new String[] {"US0124", "US0130", "US0132"}, new String[] {"US0125", "US0131", "US0133"}, "US0126", "US0127");
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionAttribute(CourseGroupDTO.SESSION_COURSE_GROUP_LIST, new CourseGroupDAO().getCourseGroupList());
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionBeforeTrans(CourseDTO.SESSION_COURSE, new CourseDTO());
		}
	}
}