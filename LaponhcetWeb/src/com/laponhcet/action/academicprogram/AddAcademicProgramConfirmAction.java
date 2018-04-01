package com.laponhcet.action.academicprogram;

import com.laponhcet.dao.AcademicProgramDAO;
import com.laponhcet.dto.AcademicProgramDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddAcademicProgramConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(AcademicProgramDTO.SESSION_ACADEMIC_PROGRAM, new AcademicProgramDAO(), DAOBase.DAO_ACTION_ADD);
	}
	
}

