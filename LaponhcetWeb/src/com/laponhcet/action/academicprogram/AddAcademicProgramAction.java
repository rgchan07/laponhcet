package com.laponhcet.action.academicprogram;

import java.util.ArrayList;
import java.util.List;

import com.laponhcet.dao.AcademicProgramGroupDAO;
import com.laponhcet.dao.AcademicProgramSubgroupDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.laponhcet.dto.AcademicProgramGroupDTO;
import com.laponhcet.dto.AcademicProgramSubgroupDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.dao.UserDAO;
import com.mytechnopal.dto.UserDTO;
import com.mytechnopal.dto.UserGroupDTO;

public class AddAcademicProgramAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		sessionInfo.setTransitionLink(new String[] {"US0024", "US0029"}, new String[] {"US0025", "US0030", "US0032"}, new String[] {"US0026", "US0031", "US0033"}, "US0027", "US0028");
		if(!sessionInfo.isPreviousLinkAddSubmit()) {
			List<DTOBase> userList = new UserDAO().getUserListByUserGroupCode(UserGroupDTO.USER_GROUP_TEACHER_CODE);
			List<DTOBase> teacherList = new ArrayList<DTOBase> ();
			for(DTOBase obj : userList){
				UserDTO userTeacher = (UserDTO) obj;
				userTeacher.setDisplayText(userTeacher.getName(true, false, true));
				teacherList.add(userTeacher);
			}
			setSessionAttribute(AcademicProgramGroupDTO.SESSION_ACADEMIC_PROGRAM_GROUP_LIST,  new AcademicProgramGroupDAO().getAcademicProgramGroupList());
			setSessionAttribute(AcademicProgramSubgroupDTO.SESSION_ACADEMIC_PROGRAM_SUBGROUP_LIST, new AcademicProgramSubgroupDAO().getAcademicProgramSubgroupList());
			setSessionAttribute(UserDTO.SESSION_USER_LIST, teacherList);
			setSessionBeforeTrans(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM, new AcademicProgramDTO());
		}
	}
}

