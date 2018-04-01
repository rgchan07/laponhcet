package com.laponhcet.action.academicprogram;

import com.mytechnopal.base.ActionBase;
import com.mytechnopal.PaginationData;
import com.mytechnopal.util.DTOUtil;
import com.laponhcet.dto.AcademicProgramDTO;

public class UpdateAcademicProgramAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	@Override
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			PaginationData paginationData = (PaginationData) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_PAGINATION);
			int id = getRequestInt("txtSelectedRecord");
			AcademicProgramDTO academicProgram = (AcademicProgramDTO) DTOUtil.getObjById(paginationData.getCurrentPageRecordList(), id);
			
			setSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM + "_ORIG", academicProgram);
			setSessionBeforeTrans(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM, academicProgram.getAcademicProgram());
		}	
	}
}

