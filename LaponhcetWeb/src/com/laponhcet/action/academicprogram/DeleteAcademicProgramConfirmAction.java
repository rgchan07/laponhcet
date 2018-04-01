package com.laponhcet.action.academicprogram;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.mytechnopal.PaginationData;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;
import com.mytechnopal.util.DTOUtil;

public class DeleteAcademicProgramConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
		
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
		AcademicProgramDTO academicProgram = (AcademicProgramDTO) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM);
		PaginationData paginationData = (PaginationData) getSessionAttribute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM_PAGINATION);
		DTOUtil.removeObjById(paginationData.getRecordListUnfiltered(), academicProgram.getId());
		DTOUtil.removeObjById(paginationData.getRecordList(), academicProgram.getId());
		sessionInfo.setCurrentLink(sessionInfo.getListLink());
	}
	
	protected void executeLogic() {
		execute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM, new AcademicProgramDAO(), DAOBase.DAO_ACTION_DELETE);
	}
	
}

