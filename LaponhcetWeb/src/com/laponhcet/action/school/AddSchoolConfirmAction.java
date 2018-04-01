package com.laponhcet.action.school;

import com.laponhcet.dao.SchoolDAO;
import com.laponhcet.dto.SchoolDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddSchoolConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;
	
	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(SchoolDTO.SESSION_SCHOOL, new SchoolDAO(), DAOBase.DAO_ACTION_ADD);
	}
	
}

