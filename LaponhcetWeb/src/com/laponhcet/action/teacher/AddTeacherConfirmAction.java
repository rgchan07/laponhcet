package com.laponhcet.action.teacher;

import com.laponhcet.dao.TeacherDAO;
import com.laponhcet.dto.TeacherDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddTeacherConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(TeacherDTO.SESSION_TEACHER, new TeacherDAO(), DAOBase.DAO_ACTION_ADD);
	}
}
