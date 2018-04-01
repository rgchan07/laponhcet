package com.laponhcet.action.register;

import com.laponhcet.dao.RegisterDAO;
import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.base.ActionBase;
import com.mytechnopal.base.DAOBase;

public class AddRegisterConfirmAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void validateInput() {
		validateTrans();
	}
	
	protected void setSessionVars() {
		setSessionLinkOnConfirm();
	}
	
	protected void executeLogic() {
		execute(RegisterDTO.SESSION_REGISTER, new RegisterDAO(), DAOBase.DAO_ACTION_ADD);
	}		
}
