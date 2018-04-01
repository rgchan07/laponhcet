package com.laponhcet.action.academicprogram;

import com.mytechnopal.PaginationData;
import com.mytechnopal.base.DTOBase;
import com.mytechnopal.util.DTOUtil;
import com.laponhcet.dto.AcademicProgramDTO;

public class DeleteAcademicProgramSubmitAction extends AcademicProgramAction {
	
	private static final long serialVersionUID = 1L;
	
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			PaginationData paginationData = (PaginationData) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_PAGINATION);
			DTOBase obj = DTOUtil.getObjById(paginationData.getCurrentPageRecordList(), getRequestInt("txtSelectedRecord"));
			AcademicProgramDTO academicProgram = (AcademicProgramDTO) obj;
			setSessionBeforeTrans(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM, academicProgram.getAcademicProgram());
		}	
	}
}

