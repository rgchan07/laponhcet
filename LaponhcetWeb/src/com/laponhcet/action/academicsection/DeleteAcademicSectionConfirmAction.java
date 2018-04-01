package com.laponhcet.action.academicsection;

import com.laponhcet.dao.AcademicSectionDAO;
import com.laponhcet.dto.AcademicSectionDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteAcademicSectionConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	

	protected void validateInput() {
		validateTrans();
	}
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		AcademicSectionDTO section = (AcademicSectionDTO) getSessionAttribute(AcademicSectionDTO.SESSION_ACADEMIC_SECTION);
		Pagination pagination = (Pagination) getSessionAttribute(AcademicSectionDTO.SESSION_ACADEMIC_SECTION_PAGINATION);
		DTOUtil.removeObjById(pagination.getRecordListUnfiltered(), section.getId());
		DTOUtil.removeObjById(pagination.getRecordList(), section.getId());
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(AcademicSectionDTO.SESSION_ACADEMIC_SECTION, new AcademicSectionDAO(), DAOBase.DAO_ACTION_DELETE);
	}
}

