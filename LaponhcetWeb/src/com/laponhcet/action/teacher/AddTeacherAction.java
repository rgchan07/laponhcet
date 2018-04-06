package com.laponhcet.action.teacher;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.TeacherDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.dao.CityDAO;
import com.mytechnopal.dao.ReligionDAO;
import com.mytechnopal.dto.CityDTO;
import com.mytechnopal.dto.ReligionDTO;

public class AddTeacherAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0096", "US0068", "US0073"}, new String[] {"US0069", "US0074", "US0076"}, new String[] {"US0070", "US0075", "US0077"}, "US0071", "US0072");
		
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			setSessionAttribute(CityDTO.SESSION_CITY_LIST, new CityDAO().getCityList());
			setSessionAttribute(ReligionDTO.SESSION_RELIGION_LIST, new ReligionDAO().getReligionList());
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST, new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_LIST, new AcademicProgramDAO().getAcademicProgramList());
			setSessionBeforeTrans(TeacherDTO.SESSION_TEACHER, new TeacherDTO());
		}
	}
}
