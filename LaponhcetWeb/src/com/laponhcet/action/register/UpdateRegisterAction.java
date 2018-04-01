package com.laponhcet.action.register;

import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.base.ActionBase;

public class UpdateRegisterAction extends ActionBase {

	private static final long serialVersionUID = 1L;

	
	protected void setSessionVars() {
		if(!sessionInfo.isPreviousLinkUpdateSubmit()) {
			RegisterDTO register = (RegisterDTO) getSelectedPaginationObjById(RegisterDTO.SESSION_REGISTER_PAGINATION, getRequestInt("txtSelectedRecord"));
			setSessionAttribute(RegisterDTO.SESSION_REGISTER + "_ORIG", register);
			setSessionAttribute(RegisterDTO.SESSION_REGISTER, register.getRegister());
		}
	}
}