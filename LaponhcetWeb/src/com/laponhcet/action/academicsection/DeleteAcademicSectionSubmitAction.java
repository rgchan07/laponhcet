package com.laponhcet.action.academicsection;

import com.laponhcet.dto.AcademicSectionDTO;
import com.mytechnopal.Pagination;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteAcademicSectionSubmitAction extends AcademicSectionAction {
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		// is previous link from the list
		if (!sessionInfo.isPreviousLinkUpdateSubmit()) {
			Pagination pagination = (Pagination) getSessionAttribute(AcademicSectionDTO.SESSION_ACADEMIC_SECTION_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(pagination.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			AcademicSectionDTO section = (AcademicSectionDTO) obj;
			setSessionBeforeTrans(AcademicSectionDTO.SESSION_ACADEMIC_SECTION, section.getAcademicSection());
		}
	}

}
