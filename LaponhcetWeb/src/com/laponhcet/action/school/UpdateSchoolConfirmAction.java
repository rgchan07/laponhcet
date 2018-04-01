package com.laponhcet.action.school;

import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;
import com.laponhcet.dao.SchoolDAO;
import com.laponhcet.dto.SchoolDTO;

public class UpdateSchoolConfirmAction extends ActionBase {	
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		SchoolDTO school = (SchoolDTO) getSessionAttribute(SchoolDTO.SESSION_SCHOOL);
		Pagination pagination = (Pagination) getSessionAttribute(SchoolDTO.SESSION_SCHOOL_PAGINATION);
		DTOUtil.replaceObjById(pagination.getRecordListUnfiltered(), school);
		DTOUtil.replaceObjById(pagination.getRecordList(), school);
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(SchoolDTO.SESSION_SCHOOL, new SchoolDAO(), DAOBase.DAO_ACTION_UPDATE);
	}
	
}

