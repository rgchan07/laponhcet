package com.laponhcet.action.school;

import com.mytechnopal.base.ActionBase;
import com.mytechnopal.Pagination;
import com.mytechnopal.util.DTOUtil;
import com.laponhcet.dto.SchoolDTO;

public class UpdateSchoolAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	@Override
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(SchoolDTO.SESSION_SCHOOL_PAGINATION);
			int id = getRequestInt("txtSelectedRecord");
			SchoolDTO school = (SchoolDTO) DTOUtil.getObjById(pagination.getCurrentPageRecordList(), id);
			
			setSessionAttribute(SchoolDTO.SESSION_SCHOOL + "_ORIG", school);
			setSessionBeforeTrans(SchoolDTO.SESSION_SCHOOL, school.getSchool());
		}	
	}
}

