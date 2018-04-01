package com.laponhcet.action.student;

import com.laponhcet.dao.StudentDAO;
import com.laponhcet.dto.StudentDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddStudentConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(StudentDTO.SESSION_STUDENT, new StudentDAO(), DAOBase.DAO_ACTION_ADD);
	}		
}