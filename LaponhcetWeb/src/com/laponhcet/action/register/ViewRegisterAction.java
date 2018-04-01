package com.laponhcet.action.register;

import com.laponhcet.dto.RegisterDTO;
import com.mytechnopal.base.ActionBase;

public class ViewRegisterAction extends ActionBase {
	private static final long serialVersionUID = 1L;

	protected void setSessionVars() {
		setSessionBeforeTrans(RegisterDTO.SESSION_REGISTER, getSelectedPaginationObjById(RegisterDTO.SESSION_REGISTER_PAGINATION, getRequestInt("txtSelectedRecord")));
	}
}
