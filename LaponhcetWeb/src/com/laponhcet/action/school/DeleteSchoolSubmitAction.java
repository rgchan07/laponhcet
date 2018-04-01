package com.laponhcet.action.school;

import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.laponhcet.dto.SchoolDTO;

public class DeleteSchoolSubmitAction extends SchoolAction {
	
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(SchoolDTO.SESSION_SCHOOL_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			SchoolDTO school = (SchoolDTO) obj;
			setSessionBeforeTrans(SchoolDTO.SESSION_SCHOOL, school.getSchool());
		}	
	}
}

