package com.laponhcet.action.school;

import com.laponhcet.dao.SchoolDAO;
import com.laponhcet.dto.SchoolDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteSchoolConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
		
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		SchoolDTO school = (SchoolDTO) getSessionAttribute(SchoolDTO.SESSION_SCHOOL);
		Pagination pagination = (Pagination) getSessionAttribute(SchoolDTO.SESSION_SCHOOL_PAGINATION);
		DTOUtil.removeObjById(pagination.getRecordListUnfiltered(), school.getId());
		DTOUtil.removeObjById(pagination.getRecordList(), school.getId());
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(SchoolDTO.SESSION_SCHOOL, new SchoolDAO(), DAOBase.DAO_ACTION_DELETE);
	}
	
}

